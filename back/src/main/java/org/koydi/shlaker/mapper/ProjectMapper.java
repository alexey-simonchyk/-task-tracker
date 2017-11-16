package org.koydi.shlaker.mapper;

import org.koydi.shlaker.dto.ProjectDto;
import org.koydi.shlaker.entity.Project;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = {CommentMapper.class, UserMapper.class, TaskMapper.class}
)
public interface ProjectMapper {

    @IterableMapping(qualifiedByName = "toShortProjectDto")
    List<ProjectDto> toShortProjectDtos(List<Project> projects);

    @Mappings({
            @Mapping(target = "description", ignore = true),
            @Mapping(target = "startTime", ignore = true),
            @Mapping(target = "endTime", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "developers", ignore = true),
            @Mapping(target = "tasks", ignore = true)
    })
    @Named("toShortProjectDto")
    ProjectDto toShortProjectDto(Project project);

    @Mappings({
            @Mapping(target = "comments", qualifiedByName = "toCommentDto"),
            @Mapping(target = "developers", qualifiedByName = "toShortUserDto"),
            @Mapping(target = "tasks", qualifiedByName = "toShortTaskDto")
    })
    @Named("toFullProjectDto")
    ProjectDto toFullProjectDto(Project project);

    @Mappings({
            @Mapping(target = "startTime", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "developers", ignore = true),
            @Mapping(target = "tasks", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    Project fromProjectDto(ProjectDto projectDto);
}
