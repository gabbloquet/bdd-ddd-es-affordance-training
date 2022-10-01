package io.github.gabbloquet.todolist.domain.todolist.model;

import io.github.gabbloquet.todolist.annotations.Port;
import io.github.gabbloquet.todolist.domain.todolist.TodolistCommand;

@Port
public interface TodolistCommandReceiver<CMD extends TodolistCommand> {

    void execute(CMD command);
}
