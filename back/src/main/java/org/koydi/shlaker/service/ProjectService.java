package org.koydi.shlaker.service;

import lombok.val;
import org.koydi.shlaker.entity.Project;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.exception.BadRequestException;
import org.koydi.shlaker.repository.ProjectRepository;
import org.koydi.shlaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class ProjectNotFound extends BadRequestException {

    public ProjectNotFound(String message) {
        super(message);
    }

    public ProjectNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}

@Service
@Transactional
public class ProjectService {

    final static Function<String, String> projectNotFoundErrorMessage =
            projectId -> String.format("Project with id %s not found", projectId);

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(String projectId) {
        return Optional.ofNullable(projectRepository.getFullProject(projectId))
                    .orElseThrow(() -> new ProjectNotFound(projectNotFoundErrorMessage.apply(projectId)));
    }

    public Project createProject(Project newProject) {
        newProject = projectRepository.save(newProject);
        return newProject;
    }

    public Set<User> updateProjectDevelopers(Set<User> developers, String projectId) {
        // TODO: 29.11.17 Delete this developers from all tasks
        val project = Optional
                .ofNullable(projectRepository.getProjectWithDevelopers(projectId))
                .orElseThrow(() -> new ProjectNotFound(projectNotFoundErrorMessage.apply(projectId)));

        val newDevelopers = userRepository
                .findAllByIdIn(
                        developers
                                .stream()
                                .map(User::getId)
                                .collect(Collectors.toList())
                );

        project.setDevelopers(newDevelopers);
        projectRepository.save(project);
        return newDevelopers;
    }
}
