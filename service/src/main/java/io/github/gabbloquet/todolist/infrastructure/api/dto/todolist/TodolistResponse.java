package io.github.gabbloquet.todolist.infrastructure.api.dto.todolist;

import io.github.gabbloquet.todolist.domain.model.Task;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Getter
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
public class TodolistResponse extends CollectionModel<EntityModel<Task>> {
    private List<String> tasks;

    public TodolistResponse selfRel(Supplier<Link> affordance) {
        Optional.ofNullable(affordance.get())
                .ifPresent(this::add);
        return this;
    }

    public TodolistResponse addTaskActionRel(Supplier<Link> affordance) {
        Optional.ofNullable(affordance.get())
                .ifPresent(this::add);
        return this;
    }
}
