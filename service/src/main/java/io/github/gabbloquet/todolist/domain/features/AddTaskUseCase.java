package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.features.commands.AddTask;
import io.github.gabbloquet.todolist.domain.features.events.TaskCreated;
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
public class AddTaskUseCase implements TodolistCommandReceiver<AddTask> {

    @NonNull
    private final TodolistEventBus todolistEventBus;

    @Override
    @EventListener
    public void execute(AddTask command) {
        Task task = new Task(command.description);
        TaskCreated taskCreated = TaskCreated.builder().task(task).build();

        todolistEventBus.publish(taskCreated);
    }
}
