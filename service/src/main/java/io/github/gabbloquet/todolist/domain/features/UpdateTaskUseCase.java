package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.CompleteTask;
import io.github.gabbloquet.todolist.domain.commands.ModifyTask;
import io.github.gabbloquet.todolist.domain.model.TodolistCommandReceiver;
import io.github.gabbloquet.todolist.domain.model.TodolistEventBus;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import io.github.gabbloquet.todolist.domain.features.events.TaskUpdated;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

import java.util.function.Supplier;

@DomainService
@RequiredArgsConstructor
public class UpdateTaskUseCase implements TodolistCommandReceiver<ModifyTask> {

    @NonNull
    private TaskRepository taskRepository;

    @NonNull
    private final Supplier<Todolist> todolistSupplier;

    @NonNull
    private final TodolistEventBus todolistEventBus;

    @Override
    @EventListener
    public void execute(ModifyTask modifyTask) {
        Task task = taskRepository.get(modifyTask.getTaskId());
        TaskUpdated taskUpdated = task.modify(modifyTask.getUpdate());

        todolistEventBus.publish(taskUpdated);

        todolistSupplier.get().apply(taskUpdated);
    }
}
