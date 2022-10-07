package io.github.gabbloquet.todolist.domain.task.renameTask;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.task.model.TaskEventBus;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandReceiver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@DomainService
@RequiredArgsConstructor
public class RenameTaskUseCase implements TodolistCommandReceiver<RenameTask> {

    @NonNull
    private final TaskEventBus taskEventBus;

    @Override
    @EventListener
    public void execute(RenameTask command) {
        TaskRenamed event = TaskRenamed
                .builder()
                .taskId(command.taskId)
                .description(command.update)
                .build();

        taskEventBus.publish(event);
    }
}
