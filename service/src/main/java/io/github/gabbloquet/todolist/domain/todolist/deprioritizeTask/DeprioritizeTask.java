package io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.todolist.TodolistCommand;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import lombok.*;

@DomainCommand
@Builder
@ToString
@EqualsAndHashCode
public final class DeprioritizeTask implements TodolistCommand {

    @NonNull
    public final TaskId taskId;

}
