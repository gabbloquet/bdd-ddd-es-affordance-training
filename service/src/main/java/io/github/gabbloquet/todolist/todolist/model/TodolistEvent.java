package io.github.gabbloquet.todolist.todolist.model;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.todolist.deprioritizeTask.TaskDeprioritized;
import io.github.gabbloquet.todolist.todolist.openApplication.TodolistCreated;
import io.github.gabbloquet.todolist.todolist.prioritizeTask.TaskPrioritized;

@DomainEvent
public interface TodolistEvent {

    <T> T accept(Visitor<T> visitor);

    interface Visitor<T> {

        T apply(TaskPrioritized event);

        T apply(TaskDeprioritized event);

        T apply(TodolistCreated event);
    }

}

