package org.koydi.shlaker.service;

import org.koydi.shlaker.entity.Project;
import org.koydi.shlaker.entity.ProjectStatus;
import org.koydi.shlaker.exception.BadRequestException;
import org.koydi.shlaker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(String projectId) {
        return projectRepository.getFullProject(projectId);
    }

    public Project createProject(Project newProject) {
        newProject = projectRepository.save(newProject);
        return newProject;
    }
}
