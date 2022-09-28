package io.github.gabbloquet.todolist.domain.features.events;

import io.github.gabbloquet.todolist.application.annotations.DomainEvent;
import io.github.gabbloquet.todolist.domain.models.TodolistEvent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@DomainEvent
@ToString
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskDeprioritized implements TodolistEvent {

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.apply(this);
    }
}
