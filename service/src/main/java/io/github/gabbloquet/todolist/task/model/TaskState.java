package io.github.gabbloquet.todolist.task.model;

import io.github.gabbloquet.todolist.annotations.Projection;
import io.github.gabbloquet.todolist.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.task.deleteTask.TaskDeleted;
import io.github.gabbloquet.todolist.task.renameTask.TaskRenamed;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Projection
@Getter
@Accessors(fluent = true)
public final class TaskState implements TaskEvent.Visitor<TaskState> {
    private TaskId id;
    private String description;
    private boolean isCompleted;
    private LocalDateTime creationTime;
    private String duration;

    public TaskState(List<TaskEvent> history) {
        history.forEach(this::apply);
    }

    public TaskState apply(TaskEvent event) {
        return event.accept(this);
    }

    @Override
    public TaskState apply(TaskCreated event) {
        this.id = event.taskId;
        this.description = event.description;
        this.isCompleted = event.isCompleted;
        this.creationTime = event.creationTime;
        return this;
    }

    @Override
    public TaskState apply(TaskRenamed event) {
        this.description = event.getDescription();
        return this;
    }

    @Override
    public TaskState apply(TaskCompleted event) {
        Duration duration = Duration.between(this.creationTime, event.at);

        this.isCompleted = true;
        this.duration = toStringifiedDuration(duration);
        return this;
    }

    @Override
    public TaskState apply(TaskDeleted event) {
        this.id = null;
        this.description = null;
        this.isCompleted = false;
        return this;
    }

    private String toStringifiedDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.minusDays(days).toHours();
        long minutes = duration.minusDays(days).minusHours(hours).toMinutes();
        if(days == 0 && hours == 0){
            return String.format("%d minute(s)", minutes);
        }
        if(hours == 0 && minutes == 0){
            return String.format("%d jour(s)", days);
        }
        return String.format("%d jour(s) et %d heure(s)", days, hours);
    }
}
