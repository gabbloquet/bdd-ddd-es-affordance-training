package io.github.gabbloquet.todolist.infrastructure.api.dto.tasks;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistResource;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.TodolistRelations;
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
public class TasksResponseMapper {

    public TaskResponse map(Task task) {
        TaskResponse taskResponse = TaskResponse.builder()
                .task(task.task())
                .build();

        Affordance affordance = new Affordance(
            task
        );

        return taskResponse
                .addTasksRel(affordance.tasksRel())
                .addActionsRel(affordance.actionsRel());
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class Affordance {

        private final Task task;
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
