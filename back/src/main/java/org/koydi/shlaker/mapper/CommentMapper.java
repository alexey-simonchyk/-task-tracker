package org.koydi.shlaker.mapper;

import org.koydi.shlaker.dto.CommentDto;
import org.koydi.shlaker.entity.Comment;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = UserMapper.class
)
public interface CommentMapper {

    @IterableMapping(qualifiedByName = "toCommentDto")
    @Named("toCommentsDtos")
    List<CommentDto> toCommentsDto(List<Comment> comments);

    @Named("toCommentDto")
    @Mapping(target = "user", qualifiedByName = "toShortUserDto")
    CommentDto toCommentDto(Comment comment);
}
