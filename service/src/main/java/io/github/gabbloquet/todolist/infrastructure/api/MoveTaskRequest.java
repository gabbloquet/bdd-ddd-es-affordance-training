package io.github.gabbloquet.todolist.infrastructure.api;

public record MoveTaskRequest(int id, int position) {}
