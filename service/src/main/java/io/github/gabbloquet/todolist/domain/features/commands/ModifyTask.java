package io.github.gabbloquet.todolist.domain.features.commands;

import io.github.gabbloquet.todolist.application.annotations.DomainCommand;
import io.github.gabbloquet.todolist.domain.models.TaskId;
import lombok.*;
import lombok.experimental.SuperBuilder;

@DomainCommand
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class ModifyTask extends TaskCommand {

    @NonNull
    public final TaskId taskId;

    @NonNull
    public final String update;

}
