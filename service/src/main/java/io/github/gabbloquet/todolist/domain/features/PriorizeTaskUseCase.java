package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.InPort.commands.PrioritizeTask;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class PriorizeTaskUseCase {

    private final Todolist todolist;

    public void execute(PrioritizeTask command) {
        todolist.priorize(command.task());
    }
}
