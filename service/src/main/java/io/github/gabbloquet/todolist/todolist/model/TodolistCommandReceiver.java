package io.github.gabbloquet.todolist.todolist.model;

import io.github.gabbloquet.todolist.annotations.Port;
import io.github.gabbloquet.todolist.todolist.TodolistCommand;

@Port
public interface TodolistCommandReceiver<CMD extends TodolistCommand> {

    void execute(CMD command);
}
