package io.github.gabbloquet.todolist.domain.commands;

import io.github.gabbloquet.todolist.application.annotations.DomainCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@DomainCommand
@Builder
@ToString
@Getter
public final class AddTask implements TodolistCommand {

    @NonNull
    private final String description;

}
