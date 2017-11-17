package org.koydi.shlaker.service;

import lombok.val;
import org.koydi.shlaker.entity.Task;
import org.koydi.shlaker.entity.TaskStatus;
import org.koydi.shlaker.repository.ProjectRepository;
import org.koydi.shlaker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public Task getFullTask(String taskId) {
        val task = taskRepository.getFullTask(taskId);
        return task;
    }

    public void updateTaskStatus(String taskId, TaskStatus newTaskStatus) {
        val task = taskRepository.getOne(taskId);
        task.setStatus(newTaskStatus);
        taskRepository.save(task);
    }

    public void createTask(String projectId, Task newTask) {
        val project = projectRepository.findOne(projectId);
        if (project != null) {
            newTask.setProject(project);
            taskRepository.save(newTask);
        }

    }
}
