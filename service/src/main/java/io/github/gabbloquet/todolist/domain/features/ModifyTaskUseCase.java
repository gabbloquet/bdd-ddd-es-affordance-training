package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.features.commands.ModifyTask;
import io.github.gabbloquet.todolist.domain.features.events.TaskModified;
import io.github.gabbloquet.todolist.domain.models.Task;
import io.github.gabbloquet.todolist.domain.models.Todolist;
import io.github.gabbloquet.todolist.domain.models.TodolistCommandReceiver;
import io.github.gabbloquet.todolist.domain.models.TodolistEventBus;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

import java.util.function.Supplier;

@DomainService
@RequiredArgsConstructor
public class ModifyTaskUseCase implements TodolistCommandReceiver<ModifyTask> {

    @NonNull
    private final Supplier<Task> taskSupplier;

    @NonNull
    private final TodolistEventBus todolistEventBus;

    @Override
    @EventListener
    public void execute(ModifyTask modifyTask) {
        Task task = taskSupplier.get();
        TaskModified taskModified = task.modify(modifyTask.update);

        todolistEventBus.publish(taskModified);
    }
}
