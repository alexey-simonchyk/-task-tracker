package org.koydi.shlaker.mapper;

import org.koydi.shlaker.dto.SignUpUserDto;
import org.koydi.shlaker.dto.UserDto;
import org.koydi.shlaker.entity.Company;
import org.koydi.shlaker.entity.User;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = {
                CompanyMapper.class
        }
)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "imageId", source = "image.id"),
            @Mapping(target = "role", source = "role.name"),
            @Mapping(target = "company", ignore = true)
    })
    @Named("toShortUserDto")
    UserDto toShortDto(User user);

    @Named("toFullUser")
    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "role", source = "role.name"),
            @Mapping(target = "imageId", source = "image.id"),
            @Mapping(target = "company", qualifiedByName = "toShortCompanyDto")
    })
    UserDto toFullUser(User user);

    @Named("fromUserDto")
    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "activated", ignore = true),
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "tasks", ignore = true),
            @Mapping(target = "company", ignore = true)
    })
    User fromUserDto(UserDto userDto);

    @Named("signUpMapper")
    @Mappings({
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "tasks", ignore = true),
            @Mapping(target = "activated", ignore = true),
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "company", ignore = true)
    })
    User signUpMapper(SignUpUserDto user);

    @Named("toShortUsersDtos")
    @IterableMapping(qualifiedByName = "toShortUserDto")
    List<UserDto> toUserDtos(Set<User> users);

    @IterableMapping(qualifiedByName = "fromUserDto")
    Set<User> fromUserDtos(List<UserDto> users);
}
