package io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandReceiver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@DomainService
@RequiredArgsConstructor
public class DeprioritizeTaskUseCase implements TodolistCommandReceiver<DeprioritizeTask> {

    @NonNull
    private final TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Override
    @EventListener
    public void execute(DeprioritizeTask command) {
        todolistUseCaseTransaction.start();

        todolistUseCaseTransaction.get().deprioritize(command.taskId);

        todolistUseCaseTransaction.commit();
    }
}
