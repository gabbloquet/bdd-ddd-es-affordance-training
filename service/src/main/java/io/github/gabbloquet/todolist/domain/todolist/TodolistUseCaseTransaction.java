package io.github.gabbloquet.todolist.domain.todolist;

import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistNotFound;
import lombok.NonNull;

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

}
