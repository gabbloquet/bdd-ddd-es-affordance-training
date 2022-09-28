package io.github.gabbloquet.todolist.domain.features.events;

import io.github.gabbloquet.todolist.application.annotations.DomainEvent;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.TodolistEvent;
import lombok.*;

@DomainEvent
@Getter
@Builder
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskCreated implements TodolistEvent {

    @NonNull
    private final Task task;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.apply(this);
    }

}
