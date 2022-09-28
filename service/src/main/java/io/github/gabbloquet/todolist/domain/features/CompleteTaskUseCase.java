package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.CompleteTask;
import io.github.gabbloquet.todolist.domain.features.events.TaskCompleted;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@DomainService
@RequiredArgsConstructor
public class CompleteTaskUseCase {

    @NonNull
    private TaskRepository taskRepository;

    @NonNull
    private TodolistRepository todolistRepository;

    @NonNull
    private final Supplier<Todolist> todolistSupplier;

    public void execute(CompleteTask command) {
        Task task = taskRepository.get(command.id());
        TaskCompleted taskCompleted = task.complete();
        taskRepository.save(task);

        todolistSupplier.get().apply(taskCompleted);

        todolistRepository.save(todolistSupplier.get());
    }
}
