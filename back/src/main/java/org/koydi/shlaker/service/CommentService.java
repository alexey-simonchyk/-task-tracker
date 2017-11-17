package org.koydi.shlaker.service;

import lombok.val;
import org.koydi.shlaker.entity.BaseEntity;
import org.koydi.shlaker.entity.Comment;
import org.koydi.shlaker.entity.Project;
import org.koydi.shlaker.repository.CommentRepository;
import org.koydi.shlaker.repository.ProjectRepository;
import org.koydi.shlaker.repository.TaskRepository;
import org.koydi.shlaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
        val project = projectRepository.findOne(projectId);
        val user = userRepository.findOne(comment.getUser().getId());
        if (project != null && user != null) {
            comment.setUser(user);
            commentRepository.save(comment);
            project.getComments().add(comment);
            projectRepository.save(project);
        }
    }

    public void addCommentToTask(String taskId, Comment comment) {
        val task = taskRepository.findOne(taskId);
        val user = userRepository.findOne(comment.getUser().getId());
        if (task != null && user != null) {
            comment.setUser(user);
            commentRepository.save(comment);
            task.getComments().add(comment);
            taskRepository.save(task);
        }
    }
}
