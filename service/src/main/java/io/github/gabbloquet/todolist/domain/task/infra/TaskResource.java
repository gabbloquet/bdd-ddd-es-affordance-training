package io.github.gabbloquet.todolist.domain.task.infra;

import io.github.gabbloquet.todolist.domain.task.infra.dto.TaskDto;
import io.github.gabbloquet.todolist.domain.task.infra.dto.TaskRequest;
import io.github.gabbloquet.todolist.domain.task.infra.dto.TasksResponseAssembler;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.todolist.TodolistService;
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

    @NonNull
    private final TasksResponseAssembler tasksResponseAssembler;

    @NonNull
    private final TodolistService todolistService;


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
