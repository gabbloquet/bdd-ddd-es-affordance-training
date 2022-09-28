package io.github.gabbloquet.todolist.domain.model;

import io.github.gabbloquet.todolist.application.annotations.Port;
import io.github.gabbloquet.todolist.domain.commands.TodolistCommand;

@Port
public interface TodolistCommandReceiver<CMD extends TodolistCommand> {

    void execute(CMD command);
}
