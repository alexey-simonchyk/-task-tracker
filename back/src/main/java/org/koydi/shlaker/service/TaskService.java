package org.koydi.shlaker.service;

import lombok.val;
import org.koydi.shlaker.entity.Task;
import org.koydi.shlaker.entity.TaskStatus;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.exception.BadRequestException;
import org.koydi.shlaker.repository.ProjectRepository;
import org.koydi.shlaker.repository.TaskRepository;
import org.koydi.shlaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.koydi.shlaker.service.ProjectService.projectNotFoundErrorMessage;

class TaskNotFound extends BadRequestException {

    TaskNotFound(String message) {
        super(message);
    }
}

class UserNotInTask extends BadRequestException {

    UserNotInTask(String message) {
        super(message);
    }
}

@Service
@Transactional
public class TaskService {

    static final Function<String, String> taskNotFoundErrorMessage =
            taskId -> String.format("Task with id %s not found", taskId);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Task getFullTask(String taskId) {
        val task = Optional
                .ofNullable(taskRepository.getFullTask(taskId))
                .orElseThrow(()-> new TaskNotFound(taskNotFoundErrorMessage.apply(taskId)));
        checkUserInTask(task);
        return task;
    }

    public void updateTaskStatus(String taskId, TaskStatus newTaskStatus) {
        val task = Optional
                .ofNullable(taskRepository.getOne(taskId))
                .orElseThrow(() -> new TaskNotFound(taskNotFoundErrorMessage.apply(taskId)));
        task.setStatus(newTaskStatus);
        taskRepository.save(task);
    }

    public void createTask(String projectId, Task newTask) {
        val project = Optional
                .ofNullable(projectRepository.findOne(projectId))
                .orElseThrow(() -> new ProjectNotFound(projectNotFoundErrorMessage.apply(projectId)));
        newTask.setProject(project);
        taskRepository.save(newTask);
    }

    public Set<User> updateTaskDevelopers(Set<User> developers, String taskId) {
        val task = Optional
                .ofNullable(taskRepository.getTaskWithDevelopers(taskId))
                .orElseThrow(() -> new TaskNotFound(taskNotFoundErrorMessage.apply(taskId)));

        val newDevelopers = userRepository
                .findAllByIdIn(
                        developers
                                .stream()
                                .map(User::getId)
                                .collect(Collectors.toList())
                );

        task.setDevelopers(newDevelopers);
        taskRepository.save(task);
        return newDevelopers;
    }

    private void checkUserInTask(Task task) {
        val userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean check  = task
                .getDevelopers()
                .stream()
                .noneMatch(developer -> developer.getId().equals(userId));
        if (check) {
            throw new UserNotInTask("This user not in project");
        }
    }
}
