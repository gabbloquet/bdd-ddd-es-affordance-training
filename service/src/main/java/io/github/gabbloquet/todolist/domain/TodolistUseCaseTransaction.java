package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.domain.models.TaskEvent;
import io.github.gabbloquet.todolist.domain.models.Todolist;
import io.github.gabbloquet.todolist.domain.models.TodolistEvent;
import io.github.gabbloquet.todolist.domain.models.error.TodolistNotFound;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import java.util.function.Supplier;

public class TodolistUseCaseTransaction implements Supplier<Todolist> {

    @NonNull
    private final TodolistRepository todolistRepository;

    private Todolist todolist;

    public TodolistUseCaseTransaction(@NonNull TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    public void start() {
//        TODO: par la suite, en gerant le multi todolist, en passant en param l'ID
        this.todolist = todolistRepository.get()
                .orElseThrow(TodolistNotFound::new);
    }

    @Override
    public Todolist get() {
        return todolist;
    }

    public void commit() {
        todolistRepository.save(todolist);
    }

    @EventListener
    @Order(1)
    public void onTaskEvent(TaskEvent event) {
        todolist.addUnsavedEvent(event);
    }
}
