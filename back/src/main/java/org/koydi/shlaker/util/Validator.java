package org.koydi.shlaker.util;

import org.koydi.shlaker.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Stream;

class NotAllFieldsPresent extends BadRequestException {

    NotAllFieldsPresent(String message) {
        super(message);
    }
}

@Component
public class Validator {

    @SafeVarargs
    public final <T> void Validate(T... object) {
        Stream.of(object).forEach(o -> {
            if (Objects.isNull(o)) {
                throw new NotAllFieldsPresent("Not all fields specified");
            }
        });
    }
}
