package io.github.gabbloquet.bddtraining.domain.InPort;

import io.github.gabbloquet.bddtraining.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.bddtraining.domain.Todolist;

public class TodolistServiceImpl implements TodolistService {

    private final TodolistRepository todolistRepository;

    public TodolistServiceImpl(TodolistRepository repository) {
        todolistRepository = repository;
    }

    public Todolist add(String task) {
        return todolistRepository.addTask(task);
    }

    public Todolist clear() {
        return todolistRepository.deleteAllTask();
    }

    public Todolist delete(String task) {
        return todolistRepository.deleteTask(task);
    }

    public Todolist modify(String taskToModify, String update) {
        return todolistRepository.modifyTask(taskToModify, update);
    }

    public Todolist move(String task, int position) {
        return todolistRepository.moveTask(task, position);
    }
}
