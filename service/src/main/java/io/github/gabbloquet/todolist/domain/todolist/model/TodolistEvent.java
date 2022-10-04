package io.github.gabbloquet.todolist.domain.todolist.model;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask.TaskDeprioritized;
import io.github.gabbloquet.todolist.domain.todolist.openApplication.TodolistCreated;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.TaskPrioritized;

@DomainEvent
public interface TodolistEvent {

    <T> T accept(Visitor<T> visitor);

    interface Visitor<T> {

        T apply(TaskPrioritized event);

        T apply(TaskDeprioritized event);

        T apply(TodolistCreated event);
    }

}

