package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.features.commands.DeprioritizeTask;
import io.github.gabbloquet.todolist.domain.models.Todolist;
import io.github.gabbloquet.todolist.domain.models.TodolistCommandReceiver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

import java.util.function.Supplier;

@DomainService
@RequiredArgsConstructor
public class DeprioritizeTaskUseCase implements TodolistCommandReceiver<DeprioritizeTask> {

    @NonNull
    private final Supplier<Todolist> todolistSupplier;

    @Override
    @EventListener
    public void execute(DeprioritizeTask command) {
        todolistSupplier.get().deprioritize(command.getTask());
    }
}
