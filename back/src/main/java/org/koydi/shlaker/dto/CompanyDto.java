package org.koydi.shlaker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDto {

    private String id;
    private String name;

    private List<CommandDto> commands;
    private List<UserDto> developers;
    private List<ProjectDto> projects;
}
