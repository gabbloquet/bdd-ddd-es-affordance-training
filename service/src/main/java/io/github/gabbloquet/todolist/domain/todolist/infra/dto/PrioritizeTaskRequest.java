package io.github.gabbloquet.todolist.domain.todolist.infra.dto;

import lombok.NonNull;

import java.util.UUID;

public record PrioritizeTaskRequest(@NonNull UUID id) {
}
