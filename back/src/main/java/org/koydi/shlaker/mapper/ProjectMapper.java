package org.koydi.shlaker.mapper;

import org.koydi.shlaker.dto.ProjectDto;
import org.koydi.shlaker.entity.Project;
import org.mapstruct.*;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface ProjectMapper {

    @IterableMapping(qualifiedByName = "toShortDto")
    List<ProjectDto> toShortProjectDtos(List<Project> projects);

    @Mappings({
            @Mapping(target = "description", ignore = true),
            @Mapping(target = "startTime", ignore = true),
            @Mapping(target = "endTime", ignore = true),
            @Mapping(target = "status", ignore = true)
    })
    @Named("toShortDto")
    ProjectDto toShortDto(Project project);

    ProjectDto toFullDto(Project project);
}
