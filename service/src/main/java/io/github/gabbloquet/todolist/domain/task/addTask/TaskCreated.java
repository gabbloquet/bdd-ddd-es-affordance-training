package io.github.gabbloquet.todolist.domain.task.addTask;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import lombok.*;

@DomainEvent
@Builder
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskCreated implements TaskEvent {

    @NonNull
    public final TaskId taskId;

    @NonNull
    public final String description;

    public final boolean isCompleted;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.apply(this);
    }

}
