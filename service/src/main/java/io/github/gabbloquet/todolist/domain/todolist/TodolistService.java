package io.github.gabbloquet.todolist.domain.todolist;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandBus;
import io.github.gabbloquet.todolist.domain.todolist.openApplication.OpenApplication;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DomainService
@RequiredArgsConstructor
@Slf4j
public class TodolistService {
    @NonNull
    private final TodolistRepository todolistRepository;

    @NonNull
    private final TodolistCommandBus todolistCommandBus;

    public Todolist execute(OpenApplication command) {
        log.info("[{}] command={}",
                command.getClass().getSimpleName(),
                command);

        todolistRepository.save(new Todolist());
// TODO: return nothing ?
        return new Todolist();
    }

    public Todolist execute(TodolistCommand command) {
        log.info("[{}] command={}",
                command.getClass().getSimpleName(),
                command);

        todolistCommandBus.dispatch(command);

        return new Todolist();
    }
}
