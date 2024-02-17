package io.github.gabbloquet.todolist.task.addTask;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import io.github.gabbloquet.todolist.todolist.TodolistCommand;

@DomainCommand
public interface OpenTask extends TodolistCommand {
}
