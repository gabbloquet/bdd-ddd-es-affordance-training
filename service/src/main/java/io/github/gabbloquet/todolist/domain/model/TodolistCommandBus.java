package io.github.gabbloquet.todolist.domain.model;

import io.github.gabbloquet.todolist.domain.commands.TodolistCommand;

public interface TodolistCommandBus {
    void dispatch(TodolistCommand command);
}
