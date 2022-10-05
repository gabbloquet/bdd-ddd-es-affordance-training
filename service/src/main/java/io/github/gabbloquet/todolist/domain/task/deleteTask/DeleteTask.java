package io.github.gabbloquet.todolist.domain.task.deleteTask;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.task.TaskCommand;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@DomainCommand
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class DeleteTask extends TaskCommand {
}
