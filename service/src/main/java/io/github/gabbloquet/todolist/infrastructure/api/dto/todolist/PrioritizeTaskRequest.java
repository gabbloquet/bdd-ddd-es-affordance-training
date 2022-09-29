package io.github.gabbloquet.todolist.infrastructure.api.dto.todolist;

import lombok.NonNull;

import java.util.UUID;

public record PrioritizeTaskRequest(@NonNull UUID id) {
}
