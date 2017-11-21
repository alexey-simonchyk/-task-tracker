package org.koydi.shlaker.mapper;

import org.koydi.shlaker.dto.UserDto;
import org.koydi.shlaker.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "imageId", source = "image.id")
    })
    @Named("toShortUserDto")
    UserDto toShortDto(User user);

    @Named("fromUserDto")
    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "image", ignore = true)
    })
    User fromUserDto(UserDto userDto);
}
