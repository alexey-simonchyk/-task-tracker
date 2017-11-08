package org.koydi.shlaker.controller;

import org.koydi.shlaker.dto.ProjectDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @GetMapping("/")
    public List<ProjectDto> getProjects() {

        return new ArrayList<>();
    }

    @PostMapping("/")
    public ProjectDto createProject(@RequestBody ProjectDto project) {
        return project;
    }

    @GetMapping("/{project_id}")
    public ProjectDto getProject(@PathVariable("project_id") String projectId) {
        return new ProjectDto();
    }

}
