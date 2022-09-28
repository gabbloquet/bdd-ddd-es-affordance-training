package io.github.gabbloquet.todolist.domain.model;

import io.github.gabbloquet.todolist.application.annotations.DomainEvent;
import io.github.gabbloquet.todolist.domain.features.events.*;

@DomainEvent
public interface TodolistEvent {

    <T> T accept(Visitor<T> visitor);

    interface Visitor<T> {
        T apply(TaskCompleted event);

        T apply(TaskCreated event);

        T apply(TaskPrioritized event);

        T apply(TaskDeprioritized event);

        T apply(TaskUpdated event);

        T apply(TodolistCreated event);
    }

}

