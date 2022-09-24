package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.application.annotations.Port;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;

@Port
public interface TodolistService {

    Todolist addTask(Task task);

    Todolist completeTask(Task task);

    void openTodolist();
}
