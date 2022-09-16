package io.github.gabbloquet.todolist.infrastructure.api.dto.todolist;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistResource;
import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.ActionDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class TodolistResponseMapper {

    public TodolistResponse map(Todolist todolist) {
        TodolistResponse todolistResponse = TodolistResponse.builder()
                .todolist(toTodolistDto(todolist))
                .actions(toActionDto(todolist))
                .build();

        Affordance affordance = new Affordance(
            todolist.tasks()
        );

        return todolistResponse
                .addTasksRel(affordance.tasksRel())
                .addActionsRel(affordance.actionsRel());
    }

    private List<ActionDto> toActionDto(Todolist todolist) {
        return null;
    }

    private TodolistDto toTodolistDto(Todolist todolist) {
        return null;
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class Affordance {

        private final List<Task> tasks;
        public Supplier<Link> tasksRel() {
            return () -> linkTo(methodOn(TodolistResource.class)
                    .getTodolist())
                    .withRel(TodolistRelations.TASKS)
                    .withName("Add tasks");
        }

        public Supplier<Link> actionsRel() {
            return () -> linkTo(methodOn(TodolistResource.class)
                    .getTodolist())
                    .withRel(TodolistRelations.TASKS)
                    .withName("Add tasks");
        }
    }
}
