package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.InPort.TaskService;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.TaskDto;
import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.TasksResponseMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
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
        return tasksResponseMapper.map(TaskDto.from(task));
    }

//    @PostMapping()
//    public EntityModel<TaskDto> addTask(@RequestBody String task) {
//        Task createdTask = taskService.add(task);
//        return tasksResponseMapper.map(createdTask);
//    }
//
//    @PutMapping("/{id}")
//    public EntityModel<TaskDto> modifyTask(@PathVariable int id, @RequestBody String update) {
//        Task task = taskService.modify(id, update);
//        return EntityModel.of(task);
//        return tasksResponseMapper.map(task);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteTask(@PathVariable int id) {
//        taskService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
