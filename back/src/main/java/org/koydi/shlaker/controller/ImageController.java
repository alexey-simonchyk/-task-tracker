package org.koydi.shlaker.controller;

import lombok.val;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.koydi.shlaker.exception.ServerErrorException;
import org.koydi.shlaker.service.ImageService;
import org.koydi.shlaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.function.Function;

@Controller
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;
    private static final String JPG_FORMAT = "jpg";
    private static final String PNG_FORMAT = "png";

    private static final Function<String, String> imageIoError =
            imagePath -> String.format("Image not found by path %s", imagePath);

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/default/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) {

        String imagePath = String.format("image/default/%s.jpg", imageName);
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

    @GetMapping(value = "/{image_id}")
    public ResponseEntity<byte[]> getUserImage(@PathVariable("image_id") String imageId) {

        val image = imageService.getImage(imageId);
        Resource imageResource = new ClassPathResource(image.getPath());
        String extension = FilenameUtils.getExtension(imageResource.getFilename());

        MediaType imageType =
                extension.equals(JPG_FORMAT) ? MediaType.IMAGE_JPEG :
                        extension.equals(PNG_FORMAT) ? MediaType.IMAGE_PNG : MediaType.ALL;


        byte[] bytes;
        try {
            bytes = IOUtils.toByteArray(imageResource.getInputStream());
        } catch (IOException e) {
            throw new ServerErrorException(imageIoError.apply(image.getPath()), e);
        }
        return ResponseEntity
                .ok()
                .contentType(imageType)
                .body(bytes);
    }

}
