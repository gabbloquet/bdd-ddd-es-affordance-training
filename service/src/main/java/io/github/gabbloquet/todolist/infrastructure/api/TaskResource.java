package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.TaskDto;
import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.TaskRequest;
import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.TasksResponseAssembler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskResource {

    private final TodolistService todolistService;

    @NonNull
    private final TasksResponseAssembler tasksResponseAssembler;

    @GetMapping("/{id}")
    public EntityModel<TaskDto> getTask(@PathVariable UUID id) {
//        Task task = todolistService.getTask(id);
        Task task = new Task("toto");
        return tasksResponseAssembler.map(TaskDto.from(task));
    }

    @PostMapping()
    public EntityModel<TaskDto> addTask(@RequestBody TaskRequest taskRequest) {
//        Task createdTask = todolistService.addTask(taskRequest.description());
        Task createdTask = new Task("toto");
        return tasksResponseAssembler.map(TaskDto.from(createdTask));
    }

    @PutMapping("/{id}")
    public EntityModel<TaskDto> modifyTask(@RequestBody TaskRequest taskRequest, @PathVariable UUID id) {
        Task task = new Task("toto");
//        Task task = todolistService.modifyTask(id, taskRequest.description());
        return tasksResponseAssembler.map(TaskDto.from(task));
    }

    @DeleteMapping("/{id}")
    public RepresentationModel<?> deleteTask(@PathVariable UUID id) {
//        todolistService.deleteTask(id);
        return tasksResponseAssembler.get();
    }
}