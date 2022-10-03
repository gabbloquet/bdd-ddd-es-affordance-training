package io.github.gabbloquet.todolist.domain.task.infra.dto;

import io.github.gabbloquet.todolist.domain.task.model.TaskState;

import java.util.UUID;

public record TaskDto(UUID id, String description, boolean isCompleted) {
    public static TaskDto from(TaskState task) {
        return new TaskDto(task.getId(), task.getDescription(), task.isCompleted());
    }
}
