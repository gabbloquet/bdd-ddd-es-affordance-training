package io.github.gabbloquet.todolist.infrastructure.api.dto.todolist;

import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.MoveTaskRequest;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistResource;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Service
@RequiredArgsConstructor
public class TodolistResponseAssembler {

    private final TodolistResource todolistResource = methodOn(TodolistResource.class);

    public EntityModel<Todolist> map(Todolist todolist) {
        MoveTaskRequest taskRequest = new MoveTaskRequest(0, 0);

        return EntityModel.of( //
                todolist, //
                getSelfLink(),
                getMoveTaskAffordance(taskRequest));
    }

    private Link getMoveTaskAffordance(MoveTaskRequest taskRequest) {
        return linkTo(todolistResource.move(taskRequest)).withRel("move").withTitle("Move a task");
    }

    private Link getSelfLink() {
        return linkTo(todolistResource.get()).withSelfRel().withTitle("Get Todolist");
    }
}
