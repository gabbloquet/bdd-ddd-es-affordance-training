package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.ModifyTask;
import io.github.gabbloquet.todolist.domain.commands.OpenApplication;
import io.github.gabbloquet.todolist.domain.features.events.TaskUpdated;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class OpenApplicationUseCase {
    @NonNull
    private TodolistRepository todolistRepository;

    private final Todolist todolist;

    public void execute(OpenApplication openApplication) {
        todolist.create();

        todolistRepository.save(todolist);
    }
}
