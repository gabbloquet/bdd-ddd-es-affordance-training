package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.ModifyTask;
import io.github.gabbloquet.todolist.domain.model.TodolistEventBus;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import io.github.gabbloquet.todolist.domain.features.events.TaskUpdated;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@DomainService
@RequiredArgsConstructor
public class UpdateTaskUseCase {

    @NonNull
    private TaskRepository taskRepository;

    @NonNull
    private TodolistRepository todolistRepository;

    @NonNull
    private final Supplier<Todolist> todolistSupplier;

    @NonNull
    private final TodolistEventBus todolistEventBus;

    public void execute(ModifyTask modifyTask) {
        Task task = taskRepository.get(modifyTask.taskId());
        TaskUpdated taskUpdated = task.modify(modifyTask.update());

        todolistEventBus.publish(taskUpdated);

        todolistSupplier.get().apply(taskUpdated);

        todolistRepository.save(todolistSupplier.get());
    }
}
