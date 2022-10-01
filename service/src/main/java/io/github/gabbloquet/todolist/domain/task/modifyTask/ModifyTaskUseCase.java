package io.github.gabbloquet.todolist.domain.task.modifyTask;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandReceiver;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistEventBus;
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
