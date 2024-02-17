package io.github.gabbloquet.todolist.todolist.infra.dto;

import io.github.gabbloquet.todolist.task.model.TaskId;
import lombok.NonNull;

public record PrioritizeTaskRequest(@NonNull TaskId id) {
}
