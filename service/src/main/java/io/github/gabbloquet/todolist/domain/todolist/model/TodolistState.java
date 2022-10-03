package io.github.gabbloquet.todolist.domain.todolist.model;

import io.github.gabbloquet.todolist.annotations.Projection;
import io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask.TaskDeprioritized;
import io.github.gabbloquet.todolist.domain.todolist.openApplication.TodolistCreated;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.TaskPrioritized;
import io.github.gabbloquet.todolist.domain.todolist.tmp.TaskAdded;
import lombok.Getter;

import java.util.List;

@Projection
@Getter
public final class TodolistState implements TodolistEvent.Visitor<TodolistState> {

    private List<Todolist.Task> tasks;

    public TodolistState(List<TodolistEvent> history) {
        history.forEach(this::apply);
    }

    public TodolistState apply(TodolistEvent event) {
        return event.accept(this);
    }

    @Override
    public TodolistState apply(TaskPrioritized event) {
        return null;
    }

    @Override
    public TodolistState apply(TaskDeprioritized event) {
        return null;
    }

    @Override
    public TodolistState apply(TodolistCreated event) {
        return null;
    }

    @Override
    public TodolistState apply(TaskAdded event) {
        return null;
    }
}
