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

    public Todolist delete(int id) {
        var currentTodolist = todolistRepository.get();
        currentTodolist.delete(id);
        return todolistRepository.save(currentTodolist);
    }

    public Todolist modify(int id, String update) {
        var currentTodolist = todolistRepository.get();
        currentTodolist.modify(id, update);
        return todolistRepository.save(currentTodolist);
    }

    public Todolist move(int id, int position) {
        var currentTodolist = todolistRepository.get();
        currentTodolist.move(id, position);
        return todolistRepository.save(currentTodolist);
    }
}
