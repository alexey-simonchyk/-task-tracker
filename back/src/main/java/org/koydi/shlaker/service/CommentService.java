package org.koydi.shlaker.service;

import lombok.val;
import org.koydi.shlaker.entity.Comment;
import org.koydi.shlaker.repository.CommentRepository;
import org.koydi.shlaker.repository.ProjectRepository;
import org.koydi.shlaker.repository.TaskRepository;
import org.koydi.shlaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.koydi.shlaker.service.ProjectService.projectNotFoundErrorMessage;
import static org.koydi.shlaker.service.TaskService.taskNotFoundErrorMessage;
import static org.koydi.shlaker.service.UserService.userNotFoundErrorMessage;

@Service
@Transactional
public class CommentService {

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    @Autowired
    public CommentService(ProjectRepository projectRepository, TaskRepository taskRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public void addCommentToProject(String projectId, Comment comment) {
        val project = Optional
                .ofNullable(projectRepository.getProjectWithComments(projectId))
                .orElseThrow(() -> new ProjectNotFound(projectNotFoundErrorMessage.apply(projectId)));

        val user = Optional
                .ofNullable(userRepository.getFullUser(comment.getUser().getId()))
                .orElseThrow(() -> new UserNotFound(userNotFoundErrorMessage.apply(comment.getUser().getId())));

        comment.setUser(user);
        commentRepository.save(comment);
        project.getComments().add(comment);
        projectRepository.save(project);
    }

    public void addCommentToTask(String taskId, Comment comment) {
        val task = Optional
                .ofNullable(taskRepository.getTaskWithComments(taskId))
                .orElseThrow(() -> new TaskNotFound(taskNotFoundErrorMessage.apply(taskId)));

        val user = Optional
                .ofNullable(userRepository.getFullUser(comment.getUser().getId()))
                .orElseThrow(() -> new UserNotFound(userNotFoundErrorMessage.apply(comment.getUser().getId())));

        comment.setUser(user);
        commentRepository.save(comment);
        task.getComments().add(comment);
        taskRepository.save(task);
    }
}
