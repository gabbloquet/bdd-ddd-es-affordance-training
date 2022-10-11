package io.github.gabbloquet.todolist.domain.todolist.openApplication;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandReceiver;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.PrioritizeTask;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@DomainService
@RequiredArgsConstructor
public class OpenApplicationUseCase implements TodolistCommandReceiver<OpenApplication> {

    @NonNull
    private final TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Override
    @EventListener
    public void execute(OpenApplication command) {
        todolistUseCaseTransaction.start();
    }
}