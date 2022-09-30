package io.github.gabbloquet.todolist.domain.features.commands;

import io.github.gabbloquet.todolist.application.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.models.Task;
import io.github.gabbloquet.todolist.domain.models.TaskId;
import lombok.*;

@DomainCommand
@Builder
@ToString
@EqualsAndHashCode
public final class DeprioritizeTask implements TodolistCommand {

    @NonNull
    public final TaskId taskId;

}
