package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.AddTask;
import io.github.gabbloquet.todolist.domain.features.events.TaskCreated;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@DomainService
@RequiredArgsConstructor
public class AddTaskUseCase {

    @NonNull
    private TaskRepository taskRepository;

    @NonNull
    private TodolistRepository todolistRepository;

    @NonNull
    private final Supplier<Todolist> todolistSupplier;

    public void execute(AddTask command) {
        Task task = new Task(command.description());
        taskRepository.save(task);

        todolistSupplier.get().apply(TaskCreated.builder().task(task).build());

        todolistRepository.save(todolistSupplier.get());
    }
}
