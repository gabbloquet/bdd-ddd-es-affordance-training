package io.github.gabbloquet.todolist.todolist.model;

public class TodolistNotFound extends RuntimeException {
    public TodolistNotFound() {
        super("Todolist not found.");
    }
}
