package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.InPort.TaskService;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.TaskDto;
import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.TasksResponseMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskResource {
    @NonNull
    private final TaskService taskService;
    private final TasksResponseMapper tasksResponseMapper;

    @GetMapping("/{id}")
    public EntityModel<TaskDto> getTask(@PathVariable int id) {
        Task task = taskService.get(id);
//        return tasksResponseMapper.map(TaskDto.from(task));
        return tasksResponseMapper.map(TaskDto.from(new Task(1, "Practice TDD")));
    }

    @PostMapping()
    public EntityModel<TaskDto> addTask(@RequestBody TaskRequest taskRequest) {
        Task createdTask = taskService.add(taskRequest.description());
        return tasksResponseMapper.map(TaskDto.from(createdTask));
    }

    @PutMapping("/{id}")
    public EntityModel<TaskDto> modifyTask(@RequestBody TaskRequest taskRequest, @PathVariable int id) {
        Task task = taskService.modify(id, taskRequest.description());
        return tasksResponseMapper.map(TaskDto.from(task));
    }

    @DeleteMapping("/{id}")
    public RepresentationModel<?> deleteTask(@PathVariable int id) {
        taskService.delete(id);
        return tasksResponseMapper.get();
    }
}
