package io.github.gabbloquet.todolist.domain.features.events;

import io.github.gabbloquet.todolist.application.annotations.DomainEvent;

@DomainEvent
public record TaskDeprioritized() {
}
