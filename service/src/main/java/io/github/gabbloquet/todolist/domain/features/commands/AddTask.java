package io.github.gabbloquet.todolist.domain.features.commands;

import io.github.gabbloquet.todolist.application.annotations.DomainCommand;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@DomainCommand
@Builder
@ToString
@EqualsAndHashCode
public final class AddTask implements StartTask, TodolistCommand {

    @NonNull
    public final String description;

}
