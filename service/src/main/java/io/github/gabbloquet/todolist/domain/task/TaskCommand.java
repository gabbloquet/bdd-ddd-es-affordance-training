package io.github.gabbloquet.todolist.domain.task;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.todolist.TodolistCommand;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@DomainCommand
@SuperBuilder
@EqualsAndHashCode
@ToString
public abstract class TaskCommand implements TodolistCommand {

    @NonNull
    public final TaskId taskId;

}
