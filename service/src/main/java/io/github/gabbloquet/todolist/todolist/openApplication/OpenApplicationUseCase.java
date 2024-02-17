package io.github.gabbloquet.todolist.todolist.openApplication;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.todolist.model.TodolistCommandReceiver;
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
