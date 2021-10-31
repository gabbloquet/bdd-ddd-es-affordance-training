package io.github.gabbloquet.bddtraining.infrastructure.spi;

import io.github.gabbloquet.bddtraining.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.bddtraining.domain.Todolist;

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
