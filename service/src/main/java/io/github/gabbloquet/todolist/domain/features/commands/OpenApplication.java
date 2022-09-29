package io.github.gabbloquet.todolist.domain.features.commands;

import io.github.gabbloquet.todolist.application.annotations.DomainCommand;
import lombok.Builder;

@DomainCommand
@Builder
public record OpenApplication() {
}
