package io.github.gabbloquet.bddtraining.domain.OutPort;

import io.github.gabbloquet.bddtraining.domain.Todolist;

public interface TodolistRepository {

    Todolist getTodolist();

    Todolist addTask(String task);

    Todolist modifyTask(String task, String update);

    Todolist deleteAllTask();

    Todolist deleteTask(String task);

    Todolist moveTask(String task, int position);
}
