package io.github.gabbloquet.todolist.domain.task.completeTask;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import lombok.*;

@DomainEvent
@ToString
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskCompleted implements TaskEvent {

    @NonNull
    private final TaskId taskId;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.apply(this);
    }
}
