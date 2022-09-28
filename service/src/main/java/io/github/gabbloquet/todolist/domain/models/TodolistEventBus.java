package io.github.gabbloquet.todolist.domain.models;

public interface TodolistEventBus {
    void publish(TodolistEvent event);
}
