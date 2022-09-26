package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.AddTask;
import io.github.gabbloquet.todolist.domain.features.events.TaskCreated;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class AddTaskUseCase {

    @NonNull
    private TaskRepository taskRepository;

    @NonNull
    private TodolistRepository todolistRepository;

    private final Todolist todolist;

    public void execute(AddTask command) {
        Task task = new Task(command.description());
        taskRepository.save(task);

        todolist.apply(new TaskCreated(task));

        todolistRepository.save(todolist);
    }
}
