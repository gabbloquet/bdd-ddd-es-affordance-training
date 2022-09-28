package io.github.gabbloquet.todolist.domain.models;

import io.github.gabbloquet.todolist.application.annotations.Aggregate;
import io.github.gabbloquet.todolist.domain.features.events.TaskCompleted;
import io.github.gabbloquet.todolist.domain.features.events.TaskUpdated;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Aggregate
@RequiredArgsConstructor
public class Task {

    @NonNull
    private final TaskId id;
    @NonNull
    private String description;
    @NonNull
    private boolean isCompleted;

    public Task(String description) {
        this.id = new TaskId();
        this.description = description;
        this.isCompleted = false;
    }

    public Task(TaskId id, String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
    }

    public Task(String description, boolean isCompleted) {
        this.id = new TaskId();
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public TaskId id() {
        return id;
    }

    public String description() {
        return description;
    }

    public TaskCompleted complete() {
        this.isCompleted = true;
        return TaskCompleted.builder().task(this).build();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public TaskUpdated modify(String update) {
        this.description = update;
        return TaskUpdated.builder().task(this).build();
    }
}