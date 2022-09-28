package io.github.gabbloquet.todolist.domain.commands;

import io.github.gabbloquet.todolist.application.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.model.Task;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;

@DomainCommand
@Builder
@ToString
@Getter
public final class DeprioritizeTask implements TodolistCommand {

    @NonNull
    private final Task task;

}
