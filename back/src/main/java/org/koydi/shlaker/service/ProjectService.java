package org.koydi.shlaker.service;

import lombok.val;
import org.koydi.shlaker.entity.Command;
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
        Optional<Command> command = Optional.ofNullable(user.getCommand());
        if (!command.isPresent()) {
            return new HashSet<>();
        }
        String commandId = command.get().getId();
        return projectRepository.getUserProjects(commandId);
    }

    public Project getProject(String projectId) {
        // todo: Add checking if user in project
        val project = Optional.ofNullable(projectRepository.getFullProject(projectId))
                    .orElseThrow(() -> new ProjectNotFound(projectNotFoundErrorMessage.apply(projectId)));
        checkUserInProject(project);
        return project;
    }

    public Project createProject(Project newProject) {
        // todo: Here add getting company and command
        newProject = projectRepository.save(newProject);
        return newProject;
    }

    private void checkUserInProject(Project project) {
        /*val userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
