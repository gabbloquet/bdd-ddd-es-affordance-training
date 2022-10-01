package io.github.gabbloquet.todolist.domain.todolist.model;

import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;

public interface TodolistEventBus {
    void publish(TodolistEvent event);

    void publish(TaskEvent event);
}
