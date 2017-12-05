package org.koydi.shlaker.service;

import lombok.val;
import org.koydi.shlaker.entity.Project;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.exception.BadRequestException;
import org.koydi.shlaker.repository.ProjectRepository;
import org.koydi.shlaker.repository.TaskRepository;
import org.koydi.shlaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class ProjectNotFound extends BadRequestException {

    ProjectNotFound(String message) {
        super(message);
    }

    ProjectNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}

class UserNotInProject extends BadRequestException {

    UserNotInProject(String message) {
        super(message);
    }
}

@Service
@Transactional
public class ProjectService {

    final static Function<String, String> projectNotFoundErrorMessage =
            projectId -> String.format("Project with id %s not found", projectId);

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          UserRepository userRepository,
                          TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Set<Project> getProjects(User user) {
        String companyId = user.getCompany().getId();
        return projectRepository.getAllByCompanyId(companyId);
    }

    public Set<Project> getProjectsUserIn(User user) {
        String commandId = user.getCommand().getId();
        return projectRepository.getUserProjects(commandId);
    }

    public Project getProject(String projectId) {
        val project = Optional.ofNullable(projectRepository.getFullProject(projectId))
                    .orElseThrow(() -> new ProjectNotFound(projectNotFoundErrorMessage.apply(projectId)));
        checkUserInProject(project);
        return project;
    }

    public Project createProject(Project newProject) {
        newProject = projectRepository.save(newProject);
        return newProject;
    }

    public Project updateProjectDevelopers(Set<User> developers, String projectId) {
        /*val project = Optional
                .ofNullable(projectRepository.getFullProject(projectId))
                .orElseThrow(() -> new ProjectNotFound(projectNotFoundErrorMessage.apply(projectId)));
        checkUserInProject(project);

        val newDevelopers = userRepository
                .findAllByIdIn(
                        developers
                                .stream()
                                .map(User::getId)
                                .collect(Collectors.toList())
                );


        val removedDevelopers = new HashSet<User>(project.getDevelopers());
        removedDevelopers.removeAll(newDevelopers);
        if (removedDevelopers.size() > 0) {
            project.getTasks()
                    .forEach(task -> task.getDevelopers().removeAll(removedDevelopers));
            taskRepository.save(project.getTasks());
        }

        project.setDevelopers(newDevelopers);
        val updatedProject = projectRepository.save(project);
        return updatedProject;*/
        return null;
    }

    private void checkUserInProject(Project project) {
        val userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isManager =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getAuthorities()
                        .stream()
                        .anyMatch(t -> t.getAuthority().equals("manager"));
        if (isManager) {
            return;
        }

        boolean check  = project
                                .getDevelopers()
                                .stream()
                                .noneMatch(developer -> developer.getId().equals(userId));
        if (check) {
            throw new UserNotInProject("This user not in project");
        }*/
    }
}
