package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.Todolist;

public interface TodolistService {

    Todolist getTodolist();

    Todolist add(String task);

    Todolist clear();

    Todolist delete(int id);

    Todolist modify(int id, String update);

    Todolist move(int id, int position);
}