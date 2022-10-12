package io.github.gabbloquet.todolist.domain.task.deleteTask;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.task.model.TaskEventBus;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandReceiver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@DomainService
@RequiredArgsConstructor
public class DeleteTaskUseCase implements TodolistCommandReceiver<DeleteTask> {

    @NonNull
    private final TaskEventBus taskEventBus;

    @Override
    @EventListener
    public void execute(DeleteTask command) {
        TaskDeleted event = TaskDeleted.builder()
                .taskId(command.taskId)
                .build();

        taskEventBus.publish(event);
    }
}
