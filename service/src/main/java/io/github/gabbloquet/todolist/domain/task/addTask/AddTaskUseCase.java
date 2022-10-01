package io.github.gabbloquet.todolist.domain.task.addTask;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskFactory;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandReceiver;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistEventBus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

import java.util.function.Supplier;

@DomainService
@RequiredArgsConstructor
public class AddTaskUseCase implements TodolistCommandReceiver<AddTask> {

    @NonNull
    private final Supplier<TaskId> taskIdProvider;

    @NonNull
    private final TodolistEventBus todolistEventBus;

    @Override
    @EventListener
    public void execute(AddTask command) {
        TaskCreated taskCreated = TaskCreated.builder()
                .taskId(taskIdProvider.get())
                .build();

        todolistEventBus.publish(taskCreated);
    }
}
