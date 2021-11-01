package io.github.gabbloquet.todolist.application.services;

import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistController;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistModelService;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class TodolistHalService implements TodolistModelService {

    private final TodolistService todolistService;

    public TodolistHalService(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    @Override
    public EntityModel<Todolist> getTodolist() {
        Todolist todolist = todolistService.getTodolist();
        return toModel(todolist);
    }

    @Override
    public EntityModel<Todolist> addTask(String task) {
        Todolist todolist = todolistService.add(task);
        return toModel(todolist);
    }

    @Override
    public EntityModel<Todolist> modifyTask(String task, String update) {
        Todolist todolist = todolistService.modify(task, update);
        return toModel(todolist);
    }

    public EntityModel<Todolist> toModel(Todolist todolist) {
        EntityModel<Todolist> todolistModel = EntityModel.of(todolist);;

        var selfLink = linkTo(methodOn(TodolistController.class).getTodolist()).withSelfRel();
        todolistModel.add(selfLink);

        return todolistModel;
    }
}
