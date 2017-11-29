package org.koydi.shlaker.controller;

import lombok.val;
import org.koydi.shlaker.dto.TaskDto;
import org.koydi.shlaker.dto.UserDto;
import org.koydi.shlaker.entity.Task;
import org.koydi.shlaker.mapper.TaskMapper;
import org.koydi.shlaker.mapper.UserMapper;
import org.koydi.shlaker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;

    @Autowired
    public TaskController(TaskService taskService, TaskMapper taskMapper, UserMapper userMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("/{task_id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTask(@PathVariable("task_id") String taskId) {
        val task = taskService.getFullTask(taskId);
        return taskMapper.toFullTaskDto(task);
    }

    @PatchMapping("/{task_id}/status")
    @ResponseStatus(HttpStatus.OK)
    public void updateTaskStatus(@PathVariable("task_id") String taskId, @RequestBody TaskDto taskDto) {
        taskService.updateTaskStatus(taskId, taskDto.getStatus());
    }

    @PostMapping("/project/{project_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@PathVariable("project_id") String projectId, @RequestBody TaskDto newTaskDto) {
        Task newTask = taskMapper.fromTaskDto(newTaskDto);
        taskService.createTask(projectId, newTask);
        return taskMapper.toShortTaskDto(newTask);
    }

    @PutMapping("/{task_id}/developers")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> updateTaskDevelopers(@PathVariable("task_id") String taskId,
                                              @RequestBody List<UserDto> developers) {
        val newDevelopers = userMapper.fromUserDtos(developers);
        val savedDevelopers = taskService.updateTaskDevelopers(newDevelopers, taskId);
        return userMapper.toUserDtos(savedDevelopers);
    }
}
