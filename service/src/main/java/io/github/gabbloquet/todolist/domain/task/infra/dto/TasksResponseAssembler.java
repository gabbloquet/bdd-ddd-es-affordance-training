package io.github.gabbloquet.todolist.domain.task.infra.dto;

import io.github.gabbloquet.todolist.domain.task.infra.TaskResource;
import io.github.gabbloquet.todolist.domain.todolist.infra.TodolistResource;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
@RequiredArgsConstructor
public class TasksResponseAssembler {

    private final TodolistResource todolistResource = methodOn(TodolistResource.class);
    private final TaskResource taskResource = methodOn(TaskResource.class);

    public EntityModel<TaskDto> map(TaskDto task, List<Link> links) {
        EntityModel<TaskDto> model = EntityModel.of( //
                task,
                getSelfLink(task)
                        .andAffordance(afford(taskResource.deleteTask(task.id()))).withRel("default").withName("Delete").withTitle("Delete a task"),
                getRenameTaskAffordance(task),
                getCompleteTaskAffordance(task),
                getTodolistAffordance()
        );
        model.add(links);
        return model;
    }

    public RepresentationModel<?> get() {
        return new RepresentationModel()
                .add(getTodolistAffordance());
    }

    private Link getSelfLink(TaskDto task) {
        return linkTo(taskResource.getTask(task.id())).withSelfRel().withTitle("Get a task");
    }

    private Link getRenameTaskAffordance(TaskDto task) {
        return linkTo(taskResource.rename(null, task.id()))
                .withRel("rename")
                .withTitle("Rename a task");
    }

    private Link getCompleteTaskAffordance(TaskDto task) {
        return linkTo(taskResource.complete(task.id()))
                .withRel("complete")
                .withName("Complete")
                .withTitle("Complete a task");
    }

    private Link getTodolistAffordance() {
        return linkTo(todolistResource.get()).withRel("todolist").withTitle("Get todolist").withName("Get all tasks, todolist");
    }
}
