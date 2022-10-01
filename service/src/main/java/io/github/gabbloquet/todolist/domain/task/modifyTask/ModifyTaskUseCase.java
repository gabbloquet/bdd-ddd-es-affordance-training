package io.github.gabbloquet.todolist.domain.task.modifyTask;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandReceiver;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistEventBus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@DomainService
@RequiredArgsConstructor
public class ModifyTaskUseCase implements TodolistCommandReceiver<ModifyTask> {

    @NonNull
    private final TodolistEventBus todolistEventBus;

    @Override
    @EventListener
    public void execute(ModifyTask command) {
        TaskModified event = TaskModified
                .builder()
                .taskId(command.taskId)
                .description(command.update)
                .build();

        todolistEventBus.publish(event);
    }
}
