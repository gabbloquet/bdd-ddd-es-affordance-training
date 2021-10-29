package io.github.gabbloquet.bddtraining.infrastructure.spi;

import io.github.gabbloquet.bddtraining.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.bddtraining.domain.Todolist;

import java.util.ArrayList;

public class InMemoryTodolistRepository implements TodolistRepository {

    private final Todolist todolist;

    public InMemoryTodolistRepository() {
        this.todolist = new Todolist(new ArrayList<>());
    }

    @Override
    public Todolist getTodolist() {
        return todolist;
    }

    @Override
    public Todolist addTask(String task) {
        todolist.add(task);
        return todolist;
    }

    @Override
    public Todolist modifyTask(String task, String update) {
        todolist.modify(task, update);
        return todolist;
    }

    @Override
    public Todolist deleteAllTask() {
        todolist.clear();
        return todolist;
    }

    @Override
    public Todolist deleteTask(String task) {
        todolist.delete(task);
        return todolist;
    }

    @Override
    public Todolist moveTask(String task, int position) {
        todolist.move(task, position);
        return todolist;
    }
}
