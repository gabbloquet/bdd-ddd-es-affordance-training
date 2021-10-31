package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.Todolist;

public class TodolistServiceImpl implements TodolistService {

    private final TodolistRepository todolistRepository;

    public TodolistServiceImpl(TodolistRepository repository) {
        todolistRepository = repository;
    }

    @Override
    public Todolist getTodolist() {
        return todolistRepository.get();
    }

    public Todolist add(String task) {
        var currentTodolist = todolistRepository.get();
        currentTodolist.add(task);
        return todolistRepository.save(currentTodolist);
    }

    public Todolist clear() {
        var currentTodolist = todolistRepository.get();
        currentTodolist.clear();
        return todolistRepository.save(currentTodolist);
    }

    public Todolist delete(String task) {
        var currentTodolist = todolistRepository.get();
        currentTodolist.delete(task);
        return todolistRepository.save(currentTodolist);
    }

    public Todolist modify(String taskToModify, String update) {
        var currentTodolist = todolistRepository.get();
        currentTodolist.modify(taskToModify, update);
        return todolistRepository.save(currentTodolist);
    }

    public Todolist move(String task, int position) {
        var currentTodolist = todolistRepository.get();
        currentTodolist.move(task, position);
        return todolistRepository.save(currentTodolist);
    }
}
