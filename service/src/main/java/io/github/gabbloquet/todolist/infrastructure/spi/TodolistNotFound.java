package io.github.gabbloquet.todolist.infrastructure.spi;

public class TodolistNotFound extends RuntimeException {
    public TodolistNotFound() {
        super("Todolist not found.");
    }
}
