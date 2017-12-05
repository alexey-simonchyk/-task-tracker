package org.koydi.shlaker.controller;

import lombok.val;
import org.koydi.shlaker.dto.ProjectDto;
import org.koydi.shlaker.dto.UserDto;
import org.koydi.shlaker.entity.Project;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.mapper.ProjectMapper;
import org.koydi.shlaker.mapper.UserMapper;
import org.koydi.shlaker.service.ProjectService;
import org.koydi.shlaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserMapper userMapper;
    private final ProjectMapper projectMapper;
    private final UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectMapper projectMapper, UserMapper userMapper, UserService userService) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('manager') or hasAuthority('director')")
    public List<ProjectDto> getProjects(Authentication authentication) {
        val user = userService.getUserInformation((String)authentication.getPrincipal());
        val projects = projectService.getProjects(user);
        return projectMapper.toShortProjectDtos(projects);
    }

    @GetMapping("/my")
    public List<ProjectDto> getUserProjects(Authentication authentication) {
        val user = userService.getUserInformation((String)authentication.getPrincipal());
        val projects = projectService.getProjectsUserIn(user);
        return projectMapper.toShortProjectDtos(projects);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('manager') or hasAuthority('director')")
    public ProjectDto createProject(@RequestBody ProjectDto projectDto) {
        Project project = projectMapper.fromProjectDto(projectDto);
        project = projectService.createProject(project);
        return projectMapper.toShortProjectDto(project);
    }

    @GetMapping("/{project_id}")
    public ProjectDto getProject(@PathVariable("project_id") String projectId) {
        val project = projectService.getProject(projectId);
        return projectMapper.toFullProjectDto(project);
    }

    @PutMapping("/{project_id}/developers")
    @PreAuthorize("hasAuthority('manager')")
    public ProjectDto updateProjectDevelopers(@PathVariable("project_id") String projectId,
                                                 @RequestBody List<UserDto> developers) {
        val newDevelopers = userMapper.fromUserDtos(developers);
        val updatedProject = projectService.updateProjectDevelopers(newDevelopers, projectId);
        return projectMapper.toFullProjectDto(updatedProject);
    }

}
