package io.github.gabbloquet.todolist.domain.model;

import lombok.NonNull;

import java.util.UUID;

public record TaskId(@NonNull UUID id) {

    public TaskId() {
        this(UUID.randomUUID());
    }
}
