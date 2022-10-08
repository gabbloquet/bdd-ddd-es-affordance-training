package io.github.gabbloquet.todolist.domain.todolist;

import io.github.gabbloquet.todolist.domain.task.TaskRepository;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistNotFound;
import lombok.NonNull;

import java.util.function.Supplier;

public class TodolistUseCaseTransaction implements Supplier<Todolist> {

    @NonNull
    private final TodolistRepository todolistRepository;

    @NonNull
    private final TaskRepository taskRepository;

    private Todolist todolist;

    public TodolistUseCaseTransaction(@NonNull TodolistRepository todolistRepository, TaskRepository taskRepository) {
        this.todolistRepository = todolistRepository;
        this.taskRepository = taskRepository;
    }

    public void start() {
        todolist = new Todolist();
        taskRepository.get()
            .forEach(task -> {
                task.getEvents().forEach(todolist::apply);
            });
    }

    @Override
    public Todolist get() {
        return todolist;
    }

    public void commit() {
        todolistRepository.save(todolist);
    }

}
