package io.github.gabbloquet.todolist.domain.task.modifyTask;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.task.TaskCommand;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@DomainCommand
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class ModifyTask extends TaskCommand {

    @NonNull
    public final String update;

}
