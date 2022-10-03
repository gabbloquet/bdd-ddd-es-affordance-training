package io.github.gabbloquet.todolist.domain.todolist.infra.dto;

import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import lombok.NonNull;

import java.util.UUID;

public record PrioritizeTaskRequest(@NonNull TaskId id) {
}
