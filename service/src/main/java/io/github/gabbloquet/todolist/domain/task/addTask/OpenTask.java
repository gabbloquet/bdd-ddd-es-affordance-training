package io.github.gabbloquet.todolist.domain.task.addTask;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.todolist.TodolistCommand;

@DomainCommand
public interface OpenTask extends TodolistCommand {
}
