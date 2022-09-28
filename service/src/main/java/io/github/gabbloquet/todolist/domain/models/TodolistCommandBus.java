package io.github.gabbloquet.todolist.domain.models;

import io.github.gabbloquet.todolist.domain.features.commands.TodolistCommand;

public interface TodolistCommandBus {
    void dispatch(TodolistCommand command);
}
