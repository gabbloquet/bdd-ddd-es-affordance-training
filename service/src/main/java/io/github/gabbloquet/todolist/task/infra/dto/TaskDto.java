package io.github.gabbloquet.todolist.task.infra.dto;

import io.github.gabbloquet.todolist.task.model.TaskState;
import io.github.gabbloquet.todolist.todolist.model.Todolist.Task;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(UUID id, String description, boolean completed, LocalDateTime creationTime, String duration) {
    public static TaskDto from(Task task) {
        return new TaskDto(task.taskId().id(), task.name(), task.done(), task.creationDateTime(), task.duration());
    }

    public static TaskDto from(TaskState task) {
        return new TaskDto(task.id().id(), task.description(), task.isCompleted(), task.creationTime(), task.duration());
    }
}
