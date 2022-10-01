package io.github.gabbloquet.todolist.domain.task.modifyTask;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@DomainEvent
@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskModified implements TaskEvent {

    private final Task task;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.apply(this);
    }
}
