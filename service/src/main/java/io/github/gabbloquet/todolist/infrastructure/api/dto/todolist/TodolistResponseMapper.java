package io.github.gabbloquet.todolist.infrastructure.api.dto.todolist;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistResource;
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
                .tasks(todolist.tasks().stream().map(Task::task).toList())
                .build();

        Affordance affordance = new Affordance(
            todolist.tasks()
        );

        return todolistResponse
                .addTasksRel(affordance.tasksRel())
                .addActionsRel(affordance.actionsRel());
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class Affordance {

        private final List<Task> tasks;
        public Supplier<Link> tasksRel() {
            return () -> linkTo(methodOn(TodolistResource.class)
                    .get())
                    .withRel(TodolistRelations.TASKS)
                    .withName("Add tasks");
        }

        public Supplier<Link> actionsRel() {
            return () -> linkTo(methodOn(TodolistResource.class)
                    .get())
                    .withRel(TodolistRelations.TASKS)
                    .withName("Add tasks");
        }
    }
}
