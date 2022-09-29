package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.features.TodolistService;
import io.github.gabbloquet.todolist.domain.features.commands.DeprioritizeTask;
import io.github.gabbloquet.todolist.domain.features.commands.OpenApplication;
import io.github.gabbloquet.todolist.domain.features.commands.PrioritizeTask;
import io.github.gabbloquet.todolist.domain.features.commands.TodolistCommand;
import io.github.gabbloquet.todolist.domain.models.TaskId;
import io.github.gabbloquet.todolist.domain.models.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.PrioritizeTaskRequest;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.TodolistDto;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.TodolistResponse;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.TodolistResponseAssembler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


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
