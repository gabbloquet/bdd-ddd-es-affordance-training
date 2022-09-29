package io.github.gabbloquet.todolist.domain.features.commands;

import io.github.gabbloquet.todolist.application.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.models.TaskId;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

@DomainCommand
@Builder
@ToString
public final class PrioritizeTask implements TodolistCommand {

    @NonNull
    public final TaskId taskId;

}
