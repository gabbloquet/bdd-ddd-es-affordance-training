package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.domain.features.events.TaskCreated;
import io.github.gabbloquet.todolist.domain.models.Task;
import io.github.gabbloquet.todolist.domain.models.TaskEvent;
import io.github.gabbloquet.todolist.domain.models.TaskId;
import io.github.gabbloquet.todolist.domain.models.Todolist;
import io.github.gabbloquet.todolist.domain.models.error.TaskNotFound;
import io.github.gabbloquet.todolist.domain.models.error.TodolistNotFound;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import java.util.function.Supplier;

public class TaskUseCaseTransaction implements Supplier<Task> {

    @NonNull
    private final TaskRepository taskRepository;

    private Task task;

    public TaskUseCaseTransaction(@NonNull TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void start(TaskId taskId) {
        this.task = taskRepository.get(taskId)
                .orElseThrow(() -> new TaskNotFound(taskId));
    }

    @Override
    public Task get() {
        return task;
    }

    public void commit() {
        taskRepository.save(task);
    }

    @EventListener
    @Order(1)
    public void onTaskCreation(TaskCreated event) {
        this.task = event.task;
    }
}
