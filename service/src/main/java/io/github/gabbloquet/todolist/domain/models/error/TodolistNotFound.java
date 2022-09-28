package io.github.gabbloquet.todolist.domain.models.error;

public class TodolistNotFound extends RuntimeException {
    public TodolistNotFound() {
        super("Todolist not found.");
    }
}
