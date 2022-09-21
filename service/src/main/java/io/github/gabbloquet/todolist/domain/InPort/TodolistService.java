package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.spi.TaskNotFound;

public interface TodolistService {

    Todolist get();

    Task getTask(int id);

    Task addTask(String task);

    Task modifyTask(int id, String update);

    void deleteTask(int id);

    Todolist move(int id, int position) throws TaskNotFound;
}