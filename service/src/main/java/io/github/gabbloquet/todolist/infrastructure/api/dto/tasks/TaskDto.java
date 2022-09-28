package io.github.gabbloquet.todolist.infrastructure.api.dto.tasks;

import io.github.gabbloquet.todolist.domain.models.Task;

import java.util.UUID;

public record TaskDto(UUID id, String description) {
    public static TaskDto from(Task task) {
        return new TaskDto(task.id().id(), task.description());
    }
}
