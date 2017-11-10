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
            @Mapping(target = "password", ignore = true)
    })
    @Named("toShortUserDto")
    UserDto toShortDto(User user);

    @IterableMapping(qualifiedByName = "toShortUserDto")
    @Named("toShortUsersDtos")
    List<UserDto> toShortUsersDtos(List<User> users);

}