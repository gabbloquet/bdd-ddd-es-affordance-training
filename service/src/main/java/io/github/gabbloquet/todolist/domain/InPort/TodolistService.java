package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.spi.TaskNotFound;

public interface TodolistService {

    Todolist get();

    Todolist move(int id, int position) throws TaskNotFound;
}