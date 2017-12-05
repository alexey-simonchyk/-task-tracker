package org.koydi.shlaker.mapper;

import org.koydi.shlaker.dto.CommentDto;
import org.koydi.shlaker.dto.UserDto;
import org.koydi.shlaker.entity.Comment;
import org.koydi.shlaker.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = UserMapper.class
)
public interface CommentMapper {

    @Named("toCommentDto")
    @Mapping(target = "user", qualifiedByName = "toShortUserDto")
    CommentDto toCommentDto(Comment comment);

    @Mappings({
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "user", qualifiedByName = "fromUserDto")
    })
    Comment fromCommentDto(CommentDto commentDto);
}
