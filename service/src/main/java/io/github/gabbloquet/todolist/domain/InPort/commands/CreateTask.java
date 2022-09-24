package io.github.gabbloquet.todolist.domain.InPort.commands;

import io.github.gabbloquet.todolist.application.annotations.DomainCommand;

@DomainCommand
public record CreateTask(String description) {
}
