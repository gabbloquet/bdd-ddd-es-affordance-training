package io.github.gabbloquet.todolist.domain.task.model;

import io.github.gabbloquet.todolist.annotations.Projection;
import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.domain.task.modifyTask.TaskModified;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Projection
@Getter
public final class TaskState implements TaskEvent.Visitor<TaskState> {
    private UUID id;
    private String description;
    private boolean isCompleted;

    public TaskState(List<TaskEvent> history) {
        history.forEach(this::apply);
    }

    public TaskState apply(TaskEvent event) {
        return event.accept(this);
    }

    @Override
    public TaskState apply(TaskCreated event) {
        this.id = event.taskId.id();
        this.description = event.description;
        this.isCompleted = event.isCompleted;
        return this;
    }

    @Override
    public TaskState apply(TaskModified event) {
        this.description = event.getDescription();
        return this;
    }

    @Override
    public TaskState apply(TaskCompleted event) {
        this.isCompleted = true;
        return this;
    }
}
