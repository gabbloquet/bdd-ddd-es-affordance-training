package io.github.gabbloquet.todolist.domain.models;

import lombok.NonNull;

import java.util.UUID;

public record TaskId(@NonNull UUID id) {

    public TaskId() {
        this(UUID.randomUUID());
    }
}
