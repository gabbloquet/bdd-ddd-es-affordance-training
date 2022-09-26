package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.DeprioritizeTask;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class DeprioritizeTaskUseCase {

    private final Todolist todolist;
    @NonNull
    private final TodolistRepository todolistRepository;

    public void execute(DeprioritizeTask command) {
        todolist.deprioritize(command.task());
        todolistRepository.save(todolist);
    }
}
