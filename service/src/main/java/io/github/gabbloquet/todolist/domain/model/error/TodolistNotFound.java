package io.github.gabbloquet.todolist.domain.model.error;

public class TodolistNotFound extends RuntimeException {
    public TodolistNotFound() {
        super("Todolist not found.");
    }
}