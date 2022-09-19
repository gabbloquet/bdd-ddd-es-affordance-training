package io.github.gabbloquet.todolist.domain.model;

public record Task(int id, String description) {
    public Task(String description) {
        this(0, description);
    }
}
