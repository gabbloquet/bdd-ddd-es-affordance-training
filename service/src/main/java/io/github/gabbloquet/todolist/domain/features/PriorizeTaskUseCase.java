package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.commands.ModifyTask;
import io.github.gabbloquet.todolist.domain.commands.PrioritizeTask;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.model.TodolistCommandReceiver;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@DomainService
@RequiredArgsConstructor
public class PriorizeTaskUseCase implements TodolistCommandReceiver<PrioritizeTask> {

    @NonNull
    private final Supplier<Todolist> todolistSupplier;
    @NonNull
    private final TodolistRepository todolistRepository;

    public void execute(PrioritizeTask command) {
        todolistSupplier.get().prioritize(command.getTask());
//        TODO: Si je save la todolist retrounée par l'evenement on me dit que ça n'est pas du même type, sping model et model
        todolistRepository.save(todolistSupplier.get());
    }
}
