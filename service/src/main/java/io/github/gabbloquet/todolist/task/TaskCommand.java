package io.github.gabbloquet.todolist.task;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import io.github.gabbloquet.todolist.task.model.TaskId;
import io.github.gabbloquet.todolist.todolist.TodolistCommand;
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
