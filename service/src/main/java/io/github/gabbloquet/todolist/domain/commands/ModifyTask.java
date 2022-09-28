package io.github.gabbloquet.todolist.domain.commands;

import io.github.gabbloquet.todolist.application.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.model.TaskId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@DomainCommand
@Builder
@ToString
@Getter
public final class ModifyTask implements TodolistCommand {

    @NonNull
    private final TaskId taskId;

    @NonNull
    private final String update;

}
