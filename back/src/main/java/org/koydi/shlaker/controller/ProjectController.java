package org.koydi.shlaker.controller;

import lombok.val;
import org.koydi.shlaker.dto.ProjectDto;
import org.koydi.shlaker.entity.Project;
import org.koydi.shlaker.mapper.ProjectMapper;
import org.koydi.shlaker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @PreAuthorize("hasAuthority('developer')")
    @GetMapping("/")
    public List<ProjectDto> getProjects() {
        val projects = projectService.getProjects();
        return projectMapper.toShortProjectDtos(projects);
    }

    @PostMapping("/")
    public ProjectDto createProject(@RequestBody ProjectDto projectDto) {
        Project project = projectMapper.fromProjectDto(projectDto);
        projectService.createProject(project);
        return projectMapper.toShortProjectDto(project);
    }

    @GetMapping("/{project_id}")
    public ProjectDto getProject(@PathVariable("project_id") String projectId) {
        val project = projectService.getProject(projectId);
        val projectDto = projectMapper.toFullProjectDto(project);
        return projectDto;
    }

}
