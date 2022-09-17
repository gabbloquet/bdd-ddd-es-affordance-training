package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.InPort.TasksService;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.TaskResponse;
import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.TasksResponseMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksResource {
    @NonNull
    private final TasksService tasksService;
    private final TasksResponseMapper tasksResponseMapper;

    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable String id) {
        Task task = tasksService.get(id);
        return tasksResponseMapper.map(task);
    }

    @PostMapping()
    public TaskResponse addTask(@RequestBody String task) {
        Task createdTask = tasksService.add(task);
        return tasksResponseMapper.map(createdTask);
    }

    @PutMapping("/{id}")
    public EntityModel<Task> modifyTask(@PathVariable int id, @RequestBody String update) {
        Task task = tasksService.modify(id, update);
        return EntityModel.of(task);
//        return tasksResponseMapper.map(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        tasksService.delete(id);
        return ResponseEntity.noContent().build();
//        return tasksResponseMapper.map(null);
    }
}
