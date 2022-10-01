package io.github.gabbloquet.todolist.domain.todolist.model;

import io.github.gabbloquet.todolist.domain.todolist.TodolistCommand;

public interface TodolistCommandBus {
    void dispatch(TodolistCommand command);
}
