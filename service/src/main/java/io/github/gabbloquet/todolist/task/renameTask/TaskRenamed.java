package io.github.gabbloquet.todolist.task.renameTask;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.task.model.TaskEvent;
import io.github.gabbloquet.todolist.task.model.TaskId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DomainEvent
@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskRenamed implements TaskEvent {

    public final TaskId taskId;

    private final String description;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.apply(this);
    }
}
