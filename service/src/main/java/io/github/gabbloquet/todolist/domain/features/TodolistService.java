package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.features.commands.OpenApplication;
import io.github.gabbloquet.todolist.domain.features.commands.TodolistCommand;
import io.github.gabbloquet.todolist.domain.models.Todolist;
import io.github.gabbloquet.todolist.domain.models.TodolistCommandBus;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DomainService
@RequiredArgsConstructor
@Slf4j
public class TodolistService {
    @NonNull
    private final TodolistUseCaseTransaction todolistUseCaseTransaction;

    @NonNull
    private final TodolistRepository todolistRepository;

    @NonNull
    private final TodolistCommandBus todolistCommandBus;

    public Todolist execute(OpenApplication command) {
        log.info("[{}] command={}",
                command.getClass().getSimpleName(),
                command);

        todolistRepository.save(new Todolist());

        return todolistUseCaseTransaction.get();
    }

    public Todolist execute(TodolistCommand command) {
        log.info("[{}] command={}",
                command.getClass().getSimpleName(),
                command);

        todolistUseCaseTransaction.start();

        todolistCommandBus.dispatch(command);

        todolistUseCaseTransaction.commit();

        return todolistUseCaseTransaction.get();
    }
}
