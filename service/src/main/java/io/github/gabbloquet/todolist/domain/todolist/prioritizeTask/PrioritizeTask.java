package io.github.gabbloquet.todolist.domain.todolist.prioritizeTask;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.todolist.TodolistCommand;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@DomainCommand
@Builder
@ToString
@EqualsAndHashCode
public final class PrioritizeTask implements TodolistCommand {

    @NonNull
    public final TaskId taskId;

}
