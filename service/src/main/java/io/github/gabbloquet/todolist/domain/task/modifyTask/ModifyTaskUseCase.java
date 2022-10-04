package io.github.gabbloquet.todolist.domain.task.modifyTask;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.task.model.TaskEventBus;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandReceiver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@DomainService
@RequiredArgsConstructor
public class ModifyTaskUseCase implements TodolistCommandReceiver<ModifyTask> {

    @NonNull
    private final TaskEventBus taskEventBus;

    @Override
    @EventListener
    public void execute(ModifyTask command) {
        TaskModified event = TaskModified
                .builder()
                .taskId(command.taskId)
                .description(command.update)
                .build();

        taskEventBus.publish(event);
    }
}
