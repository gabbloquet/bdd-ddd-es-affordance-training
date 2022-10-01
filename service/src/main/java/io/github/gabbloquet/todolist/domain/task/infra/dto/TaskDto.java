package io.github.gabbloquet.todolist.domain.task.infra.dto;

import io.github.gabbloquet.todolist.domain.task.model.Task;

import java.util.UUID;

public record TaskDto(UUID id, String description) {
    public static TaskDto from(Task task) {
        return new TaskDto(task.id().id(), task.description());
    }
}
