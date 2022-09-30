package io.github.gabbloquet.todolist.domain.models;

import io.github.gabbloquet.todolist.application.annotations.DomainEvent;
import io.github.gabbloquet.todolist.domain.features.events.*;

@DomainEvent
public interface TaskEvent {

    <T> T accept(TaskEvent.Visitor<T> visitor);

    interface Visitor<T> {
        T apply(TaskCompleted event);

        T apply(TaskCreated event);

        T apply(TaskModified event);
    }

}

