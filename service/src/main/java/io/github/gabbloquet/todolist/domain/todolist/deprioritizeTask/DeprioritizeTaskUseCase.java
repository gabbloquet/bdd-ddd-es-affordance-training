package io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandReceiver;
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
        todolistSupplier.get().deprioritize(command.taskId.id());
    }
}
