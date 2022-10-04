package io.github.gabbloquet.todolist.domain.task.addTask;

import io.github.gabbloquet.todolist.annotations.DomainCommand;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@DomainCommand
@Builder
@ToString
@EqualsAndHashCode
public final class AddTask implements OpenTask {

    @NonNull
    public final String description;

}
