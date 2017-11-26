package org.koydi.shlaker.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
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

@Controller
@RequestMapping("/image")
public class ImageController {

    private static final String JPG_FORMAT = "jpg";
    private static final String PNG_FORMAT = "png";

    @GetMapping(value = "/default/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) throws IOException {

        Resource image = new ClassPathResource("image/default/" + imageName + ".jpg");
        String extension = FilenameUtils.getExtension(image.getFilename());

        MediaType imageType =
                extension.equals(JPG_FORMAT) ? MediaType.IMAGE_JPEG :
                extension.equals(PNG_FORMAT) ? MediaType.IMAGE_PNG : MediaType.ALL;


        byte[] bytes = IOUtils.toByteArray(image.getInputStream());
        return ResponseEntity
                .ok()
                .contentType(imageType)
                .body(bytes);
    }

}
