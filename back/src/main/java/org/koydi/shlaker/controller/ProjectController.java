package org.koydi.shlaker.controller;

import lombok.val;
import org.koydi.shlaker.dto.ProjectDto;
import org.koydi.shlaker.dto.UserDto;
import org.koydi.shlaker.entity.Project;
import org.koydi.shlaker.mapper.ProjectMapper;
import org.koydi.shlaker.mapper.UserMapper;
import org.koydi.shlaker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserMapper userMapper;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectMapper projectMapper, UserMapper userMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
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
        project = projectService.createProject(project);
        return projectMapper.toShortProjectDto(project);
    }

    @GetMapping("/{project_id}")
    public ProjectDto getProject(@PathVariable("project_id") String projectId) {
        val project = projectService.getProject(projectId);
        val projectDto = projectMapper.toFullProjectDto(project);
        return projectDto;
    }

    @PutMapping("/{project_id}/developers")
    public List<UserDto> updateProjectDevelopers(@PathVariable("project_id") String projectId,
                                                 @RequestBody List<UserDto> developers) {
        val newDevelopers = userMapper.fromUserDtos(developers);
        val savedDevelopers = projectService.updateProjectDevelopers(newDevelopers, projectId);
        return userMapper.toUserDtos(savedDevelopers);
    }

}
