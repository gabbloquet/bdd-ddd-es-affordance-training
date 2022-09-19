package io.github.gabbloquet.todolist.infrastructure.api.dto.tasks;

import io.github.gabbloquet.todolist.domain.model.Task;

public record TaskDto(int id, String description) {
    public static TaskDto from(Task task) {
        return new TaskDto(task.id(), task.description());
    }
}
