package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.DeprioritizeTask;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@DomainService
@RequiredArgsConstructor
public class DeprioritizeTaskUseCase {

    @NonNull
    private final Supplier<Todolist> todolistSupplier;
    @NonNull
    private final TodolistRepository todolistRepository;

    public void execute(DeprioritizeTask command) {
        todolistSupplier.get().deprioritize(command.getTask());

        todolistRepository.save(todolistSupplier.get());
    }
}
