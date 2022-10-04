package io.github.gabbloquet.todolist.domain.todolist.infra.dto;

import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import lombok.NonNull;

public record PrioritizeTaskRequest(@NonNull TaskId id) {
}
