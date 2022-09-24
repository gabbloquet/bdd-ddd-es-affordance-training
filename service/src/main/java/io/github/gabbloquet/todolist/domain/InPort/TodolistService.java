package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.application.annotations.Port;
import io.github.gabbloquet.todolist.domain.InPort.commands.CompleteTask;
import io.github.gabbloquet.todolist.domain.InPort.commands.CreateTask;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;

@Port
public interface TodolistService {

    void addTask(CreateTask command);

    void completeTask(CompleteTask command);

    void openTodolist();
}
