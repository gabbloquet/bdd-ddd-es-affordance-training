package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.features.TodolistService;
import io.github.gabbloquet.todolist.domain.features.commands.*;
import io.github.gabbloquet.todolist.domain.models.Task;
import io.github.gabbloquet.todolist.domain.models.TaskId;
import io.github.gabbloquet.todolist.domain.models.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/todolist")
@RequiredArgsConstructor
public class TodolistResource {
    @NonNull
    private final TodolistResponseAssembler todolistResponseAssembler;

    @NonNull
    private final TodolistService todolistService;

    @GetMapping()
    public EntityModel<TodolistResponse> get() {
        Todolist todolist = todolistService.execute(OpenApplication.builder().build());
//        Todolist todolist = new Todolist(new ArrayList<>(List.of(new Task("toto"))));
        return todolistResponseAssembler.map(TodolistDto.from(todolist));
    }

    @PostMapping("/prioritize/task")
    public EntityModel<TodolistResponse> prioritize(@RequestBody PrioritizeTaskRequest request) {
        TodolistCommand command = PrioritizeTask.builder()
                .taskId(TaskId.from(request.id()))
                .build();
        Todolist todolist = todolistService.execute(command);
        return todolistResponseAssembler.map(TodolistDto.from(todolist));
    }

    @PostMapping("/deprioritize/task")
    public EntityModel<TodolistResponse> deprioritize(@RequestBody PrioritizeTaskRequest request) {
        TodolistCommand command = DeprioritizeTask.builder()
                .taskId(TaskId.from(request.id()))
                .build();
        Todolist todolist = todolistService.execute(command);
        return todolistResponseAssembler.map(TodolistDto.from(todolist));
    }
}
