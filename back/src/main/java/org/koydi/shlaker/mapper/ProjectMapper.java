package org.koydi.shlaker.mapper;

import org.koydi.shlaker.dto.ProjectDto;
import org.koydi.shlaker.entity.Project;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = CommentMapper.class
)
public interface ProjectMapper {

    @IterableMapping(qualifiedByName = "toShortDto")
    List<ProjectDto> toShortProjectDtos(List<Project> projects);

    @Mappings({
            @Mapping(target = "description", ignore = true),
            @Mapping(target = "startTime", ignore = true),
            @Mapping(target = "endTime", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "comments", ignore = true)
    })
    @Named("toShortDto")
    ProjectDto toShortDto(Project project);

    @Mapping(target = "comments", qualifiedByName = "toCommentsDtos")
    ProjectDto toFullDto(Project project);
}
