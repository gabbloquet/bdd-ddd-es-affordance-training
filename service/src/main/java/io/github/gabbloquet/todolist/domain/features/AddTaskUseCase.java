package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.AddTask;
import io.github.gabbloquet.todolist.domain.features.events.TaskCreated;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.model.TodolistCommandReceiver;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

import java.util.function.Supplier;

@DomainService
@RequiredArgsConstructor
public class AddTaskUseCase implements TodolistCommandReceiver<AddTask> {

    @NonNull
    private TaskRepository taskRepository;

    @NonNull
    private final Supplier<Todolist> todolistSupplier;

    @Override
    @EventListener
    public void execute(AddTask command) {
        Task task = new Task(command.getDescription());
        taskRepository.save(task);

        todolistSupplier.get()
                .apply(TaskCreated.builder().task(task).build());
    }
}
