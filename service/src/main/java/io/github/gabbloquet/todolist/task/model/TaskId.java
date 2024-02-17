package io.github.gabbloquet.todolist.task.model;

import lombok.NonNull;

import java.util.UUID;

public record TaskId(@NonNull UUID id) {

    public TaskId() {
        this(UUID.randomUUID());
    }

    public static TaskId from(UUID id) {
        return new TaskId(id);
    }
}
