package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.Todolist;

public interface TodolistService {

    Todolist getTodolist();

    Todolist add(String task);

    Todolist clear();

    Todolist delete(String task);

    Todolist modify(String taskToModify, String update);

    Todolist move(String task, int position);
}