package io.github.gabbloquet.todolist.domain.model;

import io.github.gabbloquet.todolist.application.annotations.Aggregate;
import io.github.gabbloquet.todolist.domain.features.TaskCompleted;
import io.github.gabbloquet.todolist.domain.features.TaskUpdated;
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
        return new TaskCompleted(this);
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public TaskUpdated modify(String update) {
        this.description = update;
        return new TaskUpdated(this);
    }
}
