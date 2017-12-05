package org.koydi.shlaker.mapper;

import org.koydi.shlaker.dto.CommandDto;
import org.koydi.shlaker.entity.Command;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = {
                UserMapper.class,
                ProjectMapper.class
        }
)
public interface CommandMapper {

    @Named("toShortCommandForProject")
    @Mappings({
            @Mapping(target = "developers", qualifiedByName = "toShortUsersDtos"),
            @Mapping(target = "projects", ignore = true)
    })
    CommandDto toShortCommandForProject(Command command);

    @Named("toFullCommandDto")
    @Mappings({
            @Mapping(target = "developers", qualifiedByName = "toShortUsersDtos"),
            @Mapping(target = "projects", qualifiedByName = "toShortProjectDtos")
    })
    CommandDto toFullCommandDto(Command command);

    @Named("toShortCommandDtos")
    @IterableMapping(qualifiedByName = "toShortCommandForProject")
    List<CommandDto> toShortCommandDtos(Set<Command> commands);
}
