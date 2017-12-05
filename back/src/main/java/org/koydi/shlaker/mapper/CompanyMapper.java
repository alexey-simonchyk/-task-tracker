package org.koydi.shlaker.mapper;

import org.koydi.shlaker.dto.CompanyDto;
import org.koydi.shlaker.entity.Company;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = {
                CommandMapper.class,
                UserMapper.class,
                ProjectMapper.class
        }
)
public interface CompanyMapper {

    @Named("toFullCompanyDto")
    @Mappings({
            @Mapping(target = "commands", qualifiedByName = "toShortCommandDtos"),
            @Mapping(target = "developers", qualifiedByName = "toShortUsersDtos"),
            @Mapping(target = "projects", qualifiedByName = "toShortProjectDtos"),
    })
    CompanyDto toFullCompanyDto(Company company);

    @Named("toShortCompanyDto")
    @Mappings({
            @Mapping(target = "commands", ignore = true),
            @Mapping(target = "developers", ignore = true),
            @Mapping(target = "projects", ignore = true),
    })
    CompanyDto toShortCompanyDto(Company company);

    @Named("toShortCompanyDtos")
    @IterableMapping(qualifiedByName = "toShortCompanyDto")
    List<CompanyDto> toShortCompanyDtos(Set<Company> companies);
}
