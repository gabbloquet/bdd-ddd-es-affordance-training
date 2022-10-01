package io.github.gabbloquet.todolist.domain.todolist.infra;

import io.github.gabbloquet.todolist.domain.todolist.TodolistRepository;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;

import java.util.Optional;

public class InMemoryTodolistRepository implements TodolistRepository {

    private Todolist todolist;

    public InMemoryTodolistRepository() {
        this.todolist = new Todolist();
    }

    @Override
    public Optional<Todolist> get() {
        return Optional.of(todolist);
    }

    @Override
    public void save(Todolist todolist) {
        this.todolist = todolist;
    }

}
