package io.github.gabbloquet.todolist.infrastructure.api.dto.todolist;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.MoveTaskRequest;
import io.github.gabbloquet.todolist.infrastructure.api.TasksResource;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistResource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Service
@RequiredArgsConstructor
public class TodolistResponseMapper {

    public EntityModel<Todolist> map(Todolist todolist) {
//        TodolistResponse todolistResponse = TodolistResponse.builder()
//                .tasks(todolist.getTasks().stream().map(Task::task).toList())
//                .build();
//
//        Affordance affordance = new Affordance();

//        return todolistResponse
//                .selfRel(affordance.selfRel())
//                .addTaskActionRel(affordance.addTaskRel());

        TodolistResource todolistResource = methodOn(TodolistResource.class);

        todolist.add(new Task("toto"));


        MoveTaskRequest taskRequest = MoveTaskRequest.builder().task("toto").position(1).build();
        return EntityModel.of( //
                todolist, //
                linkTo(todolistResource.get()).withSelfRel()
                        .andAffordance(afford(todolistResource.move(taskRequest))),
                linkTo(todolistResource.move(taskRequest)).withRel("MOVE"));
//                linkTo(todolistResource.get()).withSelfRel()
//                        .andAffordance(afford(todolistResource.move(null))));
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class Affordance {

        private final TodolistResource todolistResource = methodOn(TodolistResource.class);
        private final TasksResource tasksResource = methodOn(TasksResource.class);

        public Supplier<Link> selfRel() {
            return () -> linkTo(todolistResource.get())
                    .withSelfRel();
        }

        public Supplier<Link> addTaskRel() {
//            return () -> linkTo(tasksResource.addTask("Task Example"))
//                    .withRel(TodolistRelations.ADD_TASK)
//                    .withType("POST")
//                    .withTitle("Add a task");
            return () -> linkTo(tasksResource.addTask("Task Example"))
                    .withRel(TodolistRelations.ADD_TASK)
                    .withType("POST")
                    .withTitle("Add a task");
        }
    }
}
