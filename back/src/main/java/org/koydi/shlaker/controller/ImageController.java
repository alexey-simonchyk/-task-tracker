package org.koydi.shlaker.controller;

import lombok.val;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.koydi.shlaker.dto.ImageDto;
import org.koydi.shlaker.entity.Image;
import org.koydi.shlaker.exception.BadRequestException;
import org.koydi.shlaker.exception.ServerErrorException;
import org.koydi.shlaker.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

class EmptyImageUpload extends BadRequestException {

    EmptyImageUpload(String message) {
        super(message);
    }
}

class NotImageError extends BadRequestException {

    NotImageError(String message) {
        super(message);
    }
}

@Controller
@RequestMapping("/image")
public class ImageController {

    @Value("${store.image.path}")
    private String uploadPath;

    private final ImageService imageService;
    private static final String JPG_FORMAT = "jpg";
    private static final String PNG_FORMAT = "png";

    private static final Function<String, String> imageIoError =
            imagePath -> String.format("Image not found by path %s", imagePath);

    private static final Function<String, String> getDefaultImagePath =
            imageName -> String.format("image/default/%s.jpg", imageName);

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/default/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) {

        String imagePath = getDefaultImagePath.apply(imageName);
        Resource image = new ClassPathResource(imagePath);
        String extension = FilenameUtils.getExtension(image.getFilename());

        MediaType imageType =
                extension.equals(JPG_FORMAT) ? MediaType.IMAGE_JPEG :
                extension.equals(PNG_FORMAT) ? MediaType.IMAGE_PNG : MediaType.ALL;


        byte[] bytes;
        try {
            bytes = IOUtils.toByteArray(image.getInputStream());
        } catch (IOException e) {
            throw new ServerErrorException(imageIoError.apply(imagePath), e);
        }
        return ResponseEntity
                .ok()
                .contentType(imageType)
                .body(bytes);
    }

    @GetMapping(value = "/user/{image_id}")
    public ResponseEntity<byte[]> getUserImage(@PathVariable("image_id") String imageId) {

        val image = imageService.getImage(imageId);
        String imagePath = image.getFullPath();
        String extension = FilenameUtils.getExtension(imagePath);

        MediaType imageType =
                extension.equals(JPG_FORMAT) ? MediaType.IMAGE_JPEG :
                        extension.equals(PNG_FORMAT) ? MediaType.IMAGE_PNG : MediaType.ALL;


        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(imagePath));
        } catch (IOException e) {
            throw new ServerErrorException(imageIoError.apply(image.getPath()), e);
        }
        return ResponseEntity
                .ok()
                .contentType(imageType)
                .body(bytes);
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ImageDto> uploadImage(@RequestParam("file") MultipartFile image,
                                                Authentication authentication) {

        if (image.isEmpty()) {
            throw new EmptyImageUpload("Image not found");
        }

        Image savedImage;
        try {
            String fileExtension = FilenameUtils.getExtension(image.getOriginalFilename());

            if (!(fileExtension.equals(JPG_FORMAT) || fileExtension.equals(PNG_FORMAT))) {
                throw new NotImageError("Uploaded file must be image");
            }

            String path = String.format("%s/{image_id}.%s", uploadPath, fileExtension);
            savedImage = imageService.createImage(path, (String)authentication.getPrincipal());

            byte[] imageBytes = image.getBytes();
            System.out.println(savedImage.getFullPath());
            Files.write(Paths.get(savedImage.getFullPath()), imageBytes);

        } catch (IOException exception) {
            throw new ServerErrorException("Error while saving uploaded image", exception);
        }
        val imageDto = new ImageDto(savedImage.getId());
        return ResponseEntity
                .ok(imageDto);
    }

}
