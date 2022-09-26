package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.StartTodolist;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TodolistService {
    @NonNull
    private TodolistRepository todolistRepository;

    private final Todolist todolist;

    public void execute(StartTodolist startTodolist) {
        todolist.create();

        todolistRepository.save(todolist);
    }
}
