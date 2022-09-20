package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;

public interface TodolistService {

    Todolist get();

    Todolist move(int id, int position);
}