package io.github.gabbloquet.todolist.domain.features.commands;

import io.github.gabbloquet.todolist.application.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.models.Task;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@DomainCommand
@Builder
@ToString
@Getter
public final class DeprioritizeTask implements TodolistCommand {

    @NonNull
    private final Task task;

}
