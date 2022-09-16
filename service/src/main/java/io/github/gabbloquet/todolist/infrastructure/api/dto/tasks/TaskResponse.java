package io.github.gabbloquet.todolist.infrastructure.api.dto.tasks;

import lombok.Builder;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Builder(toBuilder = true)
public class TaskResponse extends RepresentationModel<TaskResponse> {
    private TaskDto task;
    private List<ActionDto> actions;

    public TaskResponse addTasksRel(Supplier<Link> affordance) {
        Optional.ofNullable(affordance.get())
                .ifPresent(this::add);
        return this;
    }

    public TaskResponse addActionsRel(Supplier<Link> affordance) {
        Optional.ofNullable(affordance.get())
                .ifPresent(this::add);
        return this;
    }
}
