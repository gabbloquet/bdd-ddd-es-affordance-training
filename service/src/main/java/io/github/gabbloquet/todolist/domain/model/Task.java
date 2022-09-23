package io.github.gabbloquet.todolist.domain.model;

import io.github.gabbloquet.todolist.application.annotations.DomainEntity;

@DomainEntity
public class Task {

    private int id;
    private String description;

    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
    }

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public int id() {
        return id;
    }

    public String description() {
        return description;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
