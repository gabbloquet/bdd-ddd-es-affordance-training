package io.github.gabbloquet.todolist.todolist.deprioritizeTask;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import io.github.gabbloquet.todolist.task.model.TaskId;
import io.github.gabbloquet.todolist.todolist.TodolistCommand;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@DomainCommand
@Builder
@ToString
@EqualsAndHashCode
public final class DeprioritizeTask implements TodolistCommand {

    @NonNull
    public final TaskId taskId;

}
