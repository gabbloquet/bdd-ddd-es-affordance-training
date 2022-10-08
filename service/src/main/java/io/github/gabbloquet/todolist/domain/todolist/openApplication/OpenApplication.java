package io.github.gabbloquet.todolist.domain.todolist.openApplication;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.todolist.TodolistCommand;
import lombok.Builder;

@DomainCommand
@Builder
public record OpenApplication() implements TodolistCommand {
}
