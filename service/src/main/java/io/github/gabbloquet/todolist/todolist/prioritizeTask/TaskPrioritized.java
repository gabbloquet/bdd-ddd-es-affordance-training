package io.github.gabbloquet.todolist.todolist.prioritizeTask;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.todolist.model.TodolistEvent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@DomainEvent
@ToString
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskPrioritized implements TodolistEvent {

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.apply(this);
    }
}
