package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.application.Port;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.spi.TaskNotFound;

@Port
public interface TodolistService {

    Todolist addTask(Task task);
}
