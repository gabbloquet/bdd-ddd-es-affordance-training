package io.github.gabbloquet.todolist.domain.task.completeTask;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandReceiver;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistEventBus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@DomainService
@RequiredArgsConstructor
public class CompleteTaskUseCase implements TodolistCommandReceiver<CompleteTask> {

    @NonNull
    private final TodolistEventBus todolistEventBus;

    @Override
    @EventListener
    public void execute(CompleteTask command) {
        TaskCompleted event = TaskCompleted.builder()
                .taskId(command.taskId)
                .build();

        todolistEventBus.publish(event);
    }
}
