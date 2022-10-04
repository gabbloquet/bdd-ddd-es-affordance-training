package io.github.gabbloquet.todolist.domain.todolist.prioritizeTask;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandReceiver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

import java.util.function.Supplier;

@DomainService
@RequiredArgsConstructor
public class PriorizeTaskUseCase implements TodolistCommandReceiver<PrioritizeTask> {

    @NonNull
    private final TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Override
    @EventListener
    public void execute(PrioritizeTask command) {
        todolistUseCaseTransaction.start();

        todolistUseCaseTransaction.get().prioritize(command.taskId);

        todolistUseCaseTransaction.commit();
    }
}
