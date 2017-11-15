package org.koydi.shlaker.service;

import lombok.val;
import org.koydi.shlaker.entity.Task;
import org.koydi.shlaker.entity.TaskStatus;
import org.koydi.shlaker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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
}
