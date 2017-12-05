package org.koydi.shlaker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koydi.shlaker.entity.ProjectStatus;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDto {
    private String id;
    private String name;
    private String description;
    private ProjectStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private List<CommentDto> comments;
    private List<TaskDto> tasks;
    private CommandDto command;
}
