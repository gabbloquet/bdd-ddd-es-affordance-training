package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Todolist;

public class InMemoryTodolistRepository implements TodolistRepository {

    private Todolist todolist;

    public InMemoryTodolistRepository() {
        this.todolist = new Todolist();
    }

    @Override
    public Todolist get() {
        return todolist;
    }

    @Override
    public Todolist save(Todolist todolist) {
        this.todolist = todolist;
        return todolist;
    }

}
