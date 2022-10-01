package io.github.gabbloquet.todolist.domain.todolist.openApplication;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import lombok.Builder;

@DomainCommand
@Builder
public record OpenApplication() {
}
