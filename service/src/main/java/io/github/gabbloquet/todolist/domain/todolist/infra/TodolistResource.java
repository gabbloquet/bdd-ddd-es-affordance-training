package io.github.gabbloquet.todolist.domain.todolist.infra;

import io.github.gabbloquet.todolist.domain.task.addTask.AddTask;
import io.github.gabbloquet.todolist.domain.task.addTask.OpenTask;
import io.github.gabbloquet.todolist.domain.task.infra.dto.TaskDto;
import io.github.gabbloquet.todolist.domain.task.model.TaskState;
import io.github.gabbloquet.todolist.domain.todolist.TodolistCommand;
import io.github.gabbloquet.todolist.domain.todolist.TodolistService;
import io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask.DeprioritizeTask;
import io.github.gabbloquet.todolist.domain.todolist.infra.dto.*;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.openApplication.OpenApplication;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.PrioritizeTask;
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

    @PostMapping("/add/task")
    public EntityModel<TodolistResponse> addTask(@RequestBody AddTaskRequest request) {

        OpenTask command = AddTask.builder()
                .description(request.description())
                .build();

        Todolist todolist = todolistService.execute(command);

        return todolistResponseAssembler.map(TodolistDto.from(todolist));
    }

    @PostMapping("/prioritize/task")
    public EntityModel<TodolistResponse> prioritize(@RequestBody PrioritizeTaskRequest request) {
        TodolistCommand command = PrioritizeTask.builder()
                .taskId(request.id())
                .build();

        Todolist todolist = todolistService.execute(command);

        return todolistResponseAssembler.map(TodolistDto.from(todolist));
    }

    @PostMapping("/deprioritize/task")
    public EntityModel<TodolistResponse> deprioritize(@RequestBody PrioritizeTaskRequest request) {
        TodolistCommand command = DeprioritizeTask.builder()
                .taskId(request.id())
                .build();

        Todolist todolist = todolistService.execute(command);

        return todolistResponseAssembler.map(TodolistDto.from(todolist));
    }
}
