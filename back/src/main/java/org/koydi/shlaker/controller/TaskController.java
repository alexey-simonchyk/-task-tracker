package org.koydi.shlaker.controller;

import org.koydi.shlaker.dto.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @PostMapping("/project/{project_id}")
    public TaskDto createTask(@PathVariable("project_id") String projectId,
                              @RequestBody TaskDto taskDto) {
        return new TaskDto();
    }

    @PutMapping("/{task_id}")
    public TaskDto updateTask(@PathVariable("task_id") String taskId,
                              @RequestBody TaskDto taskDto) {
        return new TaskDto();
    }
}
