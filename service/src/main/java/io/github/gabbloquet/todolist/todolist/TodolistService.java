package io.github.gabbloquet.todolist.todolist;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.todolist.model.Todolist;
import io.github.gabbloquet.todolist.todolist.model.TodolistCommandBus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DomainService
@RequiredArgsConstructor
@Slf4j
public class TodolistService {

    @NonNull
    private final TodolistCommandBus todolistCommandBus;

    @NonNull
    private final TodolistUseCaseTransaction todolistUseCaseTransaction;

    public Todolist execute(TodolistCommand command) {
        log.info("[{}] command={}",
                command.getClass().getSimpleName(),
                command);

        todolistCommandBus.dispatch(command);

        return todolistUseCaseTransaction.get();
    }
}
