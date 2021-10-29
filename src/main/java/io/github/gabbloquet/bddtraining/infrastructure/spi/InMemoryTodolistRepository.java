package io.github.gabbloquet.bddtraining.infrastructure.spi;

import io.github.gabbloquet.bddtraining.domain.Todolist;
import io.github.gabbloquet.bddtraining.domain.OutPort.TodolistRepository;

import java.util.List;

public class InMemoryTodolistRepository implements TodolistRepository {

    @Override
    public Todolist getTodolist() {
        return new Todolist(List.of());
    }

    @Override
    public Todolist addTask(String task) {
        return null;
    }

    @Override
    public Todolist modifyTask(String task, String update) {
        return null;
    }

    @Override
    public Todolist deleteAllTask() {
        return null;
    }

    @Override
    public Todolist deleteTask(String task) {
        return null;
    }

    @Override
    public Todolist moveTask(String task, int position) {
        return null;
    }
}
