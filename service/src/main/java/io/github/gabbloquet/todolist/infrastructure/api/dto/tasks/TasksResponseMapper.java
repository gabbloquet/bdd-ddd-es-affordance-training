package io.github.gabbloquet.todolist.infrastructure.api.dto.tasks;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistResource;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.TodolistRelations;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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

//        List<EntityModel<Task>> taskResources = List.of(new Task("Amazing task")).stream()
//                .map(task -> EntityModel.of(task,
//                        linkTo(tasksResource.getTask("1")).withSelfRel().withTitle("Get Task informations")
//                                .andAffordance(afford(tasksResource.modifyTask(1, null)))
//                                .andAffordance(afford(tasksResource.deleteTask(1))),
//                        linkTo(todolistResource.get()).withRel("todolist")))
//                .collect(Collectors.toList());

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
                    .withRel(TodolistRelations.ADD_TASK)
                    .withName("Add tasks");
        }

        public Supplier<Link> actionsRel() {
            return () -> linkTo(methodOn(TodolistResource.class)
                    .get())
                    .withRel(TodolistRelations.ADD_TASK)
                    .withName("Add tasks");
        }
    }
}
