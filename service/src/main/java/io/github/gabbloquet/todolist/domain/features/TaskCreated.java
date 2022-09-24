package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainEvent;
import io.github.gabbloquet.todolist.domain.model.Task;

@DomainEvent
public record TaskCreated(Task task) {
}
