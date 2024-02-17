package io.github.gabbloquet.todolist.todolist.model;

import io.github.gabbloquet.todolist.todolist.TodolistCommand;

public interface TodolistCommandBus {
    void dispatch(TodolistCommand command);
}
