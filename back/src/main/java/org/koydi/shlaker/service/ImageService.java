package org.koydi.shlaker.service;

import lombok.val;
import org.koydi.shlaker.entity.Image;
import org.koydi.shlaker.exception.BadRequestException;
import org.koydi.shlaker.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Function;

class ImageNotFound extends BadRequestException {

    ImageNotFound(String message) {
        super(message);
    }
}

@Service
@Transactional
public class ImageService {

    private final static Function<String, String> imageNotFoundErrorMessage =
            imageId -> String.format("Image with id %s not found", imageId);
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image getImage(String imageId) {
        return Optional
                .ofNullable(imageRepository.getOne(imageId))
                .orElseThrow(() -> new ImageNotFound(imageNotFoundErrorMessage.apply(imageId)));
    }

    public Image createImage(String path) {
        Image image = new Image();
        image.setPath(path);
        image = imageRepository.save(image);
        return image;
    }
}
