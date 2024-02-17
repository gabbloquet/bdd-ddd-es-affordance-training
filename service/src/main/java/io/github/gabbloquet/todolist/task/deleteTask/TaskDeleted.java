package io.github.gabbloquet.todolist.task.deleteTask;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.task.model.TaskEvent;
import io.github.gabbloquet.todolist.task.model.TaskId;
import lombok.*;

@DomainEvent
@ToString
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskDeleted implements TaskEvent {

    @NonNull
    public final TaskId taskId;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.apply(this);
    }
}
