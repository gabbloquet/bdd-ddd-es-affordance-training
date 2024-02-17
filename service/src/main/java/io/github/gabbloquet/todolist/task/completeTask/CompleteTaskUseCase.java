package io.github.gabbloquet.todolist.task.completeTask;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.task.model.TaskEventBus;
import io.github.gabbloquet.todolist.todolist.model.LocalDateTimeSupplier;
import io.github.gabbloquet.todolist.todolist.model.TodolistCommandReceiver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@DomainService
@RequiredArgsConstructor
public class CompleteTaskUseCase implements TodolistCommandReceiver<CompleteTask> {

    @NonNull
    private final TaskEventBus taskEventBus;

    @NonNull
    private final LocalDateTimeSupplier localDateTimeSupplier;

    @Override
    @EventListener
    public void execute(CompleteTask command) {
        TaskCompleted event = TaskCompleted.builder()
                .taskId(command.taskId)
                .at(localDateTimeSupplier.get())
                .build();

        taskEventBus.publish(event);
    }
}
