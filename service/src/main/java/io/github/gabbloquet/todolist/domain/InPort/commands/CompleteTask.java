package io.github.gabbloquet.todolist.domain.InPort.commands;

import io.github.gabbloquet.todolist.application.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.model.TaskId;

@DomainCommand
public record CompleteTask(TaskId id) {
}
