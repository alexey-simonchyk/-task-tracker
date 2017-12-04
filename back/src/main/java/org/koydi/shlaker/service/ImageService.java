package org.koydi.shlaker.service;

import org.koydi.shlaker.entity.Image;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.exception.BadRequestException;
import org.koydi.shlaker.repository.ImageRepository;
import org.koydi.shlaker.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    public Image getImage(String imageId) {
        return Optional
                .ofNullable(imageRepository.findOne(imageId))
                .orElseThrow(() -> new ImageNotFound(imageNotFoundErrorMessage.apply(imageId)));
    }

    public Image createImage(String path, String userId) {

        User user = userRepository.findOne(userId);

        Image image = new Image(path);
        image = imageRepository.save(image);

        user.setImage(image);
        userRepository.save(user);
        return image;
    }
}
