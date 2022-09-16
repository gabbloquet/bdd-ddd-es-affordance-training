package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.TodolistResponse;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.TodolistResponseMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todolist")
@RequiredArgsConstructor
public class TodolistResource {
    @NonNull
    private final TodolistService todolistService;

    @NonNull
    private final TodolistResponseMapper todolistResponseMapper;

    @GetMapping("")
    public TodolistResponse getTodolist() {
        Todolist todolist = todolistService.get();
        return todolistResponseMapper.map(todolist);
    }
}
