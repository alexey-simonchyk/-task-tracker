package org.koydi.shlaker.mapper;

import org.koydi.shlaker.dto.TaskDto;
import org.koydi.shlaker.entity.Task;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = {CommentMapper.class, UserMapper.class}
)
public interface TaskMapper {

    @Named("toShortTaskDto")
    @Mappings({
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "developers", qualifiedByName = "toShortUserDto")
    })
    TaskDto toShortTaskDto(Task task);

    @Mappings({
            @Mapping(target = "comments", qualifiedByName = "toCommentDto"),
            @Mapping(target = "developers", qualifiedByName = "toShortUserDto")
    })
    @Named("toFullTaskDto")
    TaskDto toFullTaskDto(Task task);
}
