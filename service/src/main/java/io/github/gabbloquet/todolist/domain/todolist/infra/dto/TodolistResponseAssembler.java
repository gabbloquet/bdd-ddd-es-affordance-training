package io.github.gabbloquet.todolist.domain.todolist.infra.dto;

import io.github.gabbloquet.todolist.domain.task.infra.dto.TaskDto;
import io.github.gabbloquet.todolist.domain.task.infra.dto.TasksResponseAssembler;
import io.github.gabbloquet.todolist.domain.todolist.infra.TodolistResource;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
@RequiredArgsConstructor
public class TodolistResponseAssembler {

    private final TasksResponseAssembler tasksResponseAssembler;

    private final TodolistResource todolistResource = methodOn(TodolistResource.class);

    public EntityModel<TodolistResponse> map(TodolistDto todolist) {
        var todolistResponse = new TodolistResponse(getTasksAffordances(todolist));

        return EntityModel.of(
                todolistResponse,
                getSelfLink(),
                getPrioritizeTaskAffordance(),
                getDeprioritizeTaskAffordance());
    }

    private List<EntityModel<TaskDto>> getTasksAffordances(TodolistDto todolist) {
        return todolist.tasks().stream()
                .map((tasksResponseAssembler::map))
                .toList();
    }

    private Link getPrioritizeTaskAffordance() {
        return linkTo(todolistResource.prioritize(null))
                .withRel("default")
                .withName("Prioritize")
                .withTitle("Prioritize a task");
    }

    private Link getDeprioritizeTaskAffordance() {
        return linkTo(todolistResource.deprioritize(null))
                .withRel("deprioritize")
                .withName("Deprioritize")
                .withTitle("Deprioritize a task");
    }

    private Link getSelfLink() {
        return linkTo(todolistResource.get()).withSelfRel().withTitle("Get todolist");
    }
}
