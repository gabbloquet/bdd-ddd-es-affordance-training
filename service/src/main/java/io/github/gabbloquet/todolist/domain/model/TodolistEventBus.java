package io.github.gabbloquet.todolist.domain.model;

public interface TodolistEventBus {
    void publish(TodolistEvent event);
}
