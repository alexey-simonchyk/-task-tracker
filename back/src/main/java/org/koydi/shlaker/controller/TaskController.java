package org.koydi.shlaker.controller;

import lombok.val;
import org.koydi.shlaker.dto.TaskDto;
import org.koydi.shlaker.mapper.TaskMapper;
import org.koydi.shlaker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin("http://localhost:4200")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/{task_id}")
    public TaskDto getTask(@PathVariable("task_id") String taskId) {
        val task = taskService.getFullTask(taskId);
        return taskMapper.toFullTaskDto(task);
    }
}
