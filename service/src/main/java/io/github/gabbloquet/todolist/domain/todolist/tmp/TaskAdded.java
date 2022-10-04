package io.github.gabbloquet.todolist.domain.todolist.tmp;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistEvent;
import lombok.*;

@DomainEvent
@Builder
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskAdded implements TodolistEvent {

    @NonNull
    public final TaskId taskId;

    public final String description;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.apply(this);
    }

}
