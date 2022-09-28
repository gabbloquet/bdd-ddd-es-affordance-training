package io.github.gabbloquet.todolist.domain.models;

import io.github.gabbloquet.todolist.application.annotations.Port;
import io.github.gabbloquet.todolist.domain.features.commands.TodolistCommand;

@Port
public interface TodolistCommandReceiver<CMD extends TodolistCommand> {

    void execute(CMD command);
}
