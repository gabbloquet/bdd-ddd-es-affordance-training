package io.github.gabbloquet.todolist.task.addTask;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.task.model.TaskEvent;
import io.github.gabbloquet.todolist.task.model.TaskId;
import lombok.*;

import java.time.LocalDateTime;

@DomainEvent
@Builder
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskCreated implements TaskEvent {

    @NonNull
    public final TaskId taskId;

    @NonNull
    public final String description;

    @NonNull
    public final LocalDateTime creationTime;

    public final boolean isCompleted;


    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.apply(this);
    }

}
