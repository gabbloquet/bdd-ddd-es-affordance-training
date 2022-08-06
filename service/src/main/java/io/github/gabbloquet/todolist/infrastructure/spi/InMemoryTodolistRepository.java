package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.Todolist;

import java.util.ArrayList;

public class InMemoryTodolistRepository implements TodolistRepository {

    private Todolist todolist;

    public InMemoryTodolistRepository() {
        this.todolist = new Todolist(new ArrayList<>());
    }

    @Override
    public Todolist get() {
        return todolist;
    }

    @Override
    public Todolist save(Todolist updatedTodolist) {
        this.todolist = updatedTodolist;
        return todolist;
    }
}
