package org.koydi.shlaker.mapper;

import org.koydi.shlaker.dto.ProjectDto;
import org.koydi.shlaker.entity.Project;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = {
                CommentMapper.class,
                UserMapper.class,
                TaskMapper.class,
                CommandMapper.class
        }
)
public interface ProjectMapper {

    @Named("toShortProjectDtos")
    @IterableMapping(qualifiedByName = "toShortProjectDto")
    List<ProjectDto> toShortProjectDtos(Set<Project> projects);

    @IterableMapping(qualifiedByName = "toShortProjectDto")
    List<ProjectDto> toShortProjectDtos(List<Project> projects);

    @Named("toShortProjectDto")
    @Mappings({
            @Mapping(target = "description", ignore = true),
            @Mapping(target = "startTime", ignore = true),
            @Mapping(target = "endTime", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "tasks", ignore = true),
            @Mapping(target = "command", ignore = true),
    })
    ProjectDto toShortProjectDto(Project project);

    @Named("toFullProjectDto")
    @Mappings({
            @Mapping(target = "comments", qualifiedByName = "toCommentDto"),
            @Mapping(target = "command", qualifiedByName = "toShortCommandForProject"),
            @Mapping(target = "tasks", qualifiedByName = "toShortTaskDto"),
    })
    ProjectDto toFullProjectDto(Project project);

    @Mappings({
            @Mapping(target = "startTime", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "tasks", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "command", ignore = true),
            @Mapping(target = "command.id", source = "command.id")
    })
    Project fromProjectDto(ProjectDto projectDto);
}
