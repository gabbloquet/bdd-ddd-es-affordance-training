package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.PrioritizeTask;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class PriorizeTaskUseCase {

    private final Todolist todolist;
    @NonNull
    private final TodolistRepository todolistRepository;

    public void execute(PrioritizeTask command) {
        todolist.prioritize(command.task());
//        TODO: Si je save la todolist retrounée par l'evenement on me dit que ça n'est pas du même type, sping model et model
        todolistRepository.save(todolist);
    }
}
