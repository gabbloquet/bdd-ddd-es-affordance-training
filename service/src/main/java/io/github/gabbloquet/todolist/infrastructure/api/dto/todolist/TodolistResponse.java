package io.github.gabbloquet.todolist.infrastructure.api.dto.todolist;

import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.ActionDto;
import lombok.Builder;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Builder(toBuilder = true)
public class TodolistResponse extends RepresentationModel<TodolistResponse> {
    private TodolistDto todolist;
    private List<ActionDto> actions;

    public TodolistResponse addTasksRel(Supplier<Link> affordance) {
        Optional.ofNullable(affordance.get())
                .ifPresent(this::add);
        return this;
    }

    public TodolistResponse addActionsRel(Supplier<Link> affordance) {
        Optional.ofNullable(affordance.get())
                .ifPresent(this::add);
        return this;
    }
}
