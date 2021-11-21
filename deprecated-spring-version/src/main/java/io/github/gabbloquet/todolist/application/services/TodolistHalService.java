package io.github.gabbloquet.todolist.application.services;

import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistController;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistModelService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.QueryParameter;
import org.springframework.hateoas.mediatype.Affordances;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;

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
    public EntityModel<Todolist> modifyTask(int id, String update) {
        Todolist todolist = todolistService.modify(id, update);
        return toModel(todolist);
    }

  @Override
  public EntityModel<Todolist> deleteTask(int id) {
    Todolist todolist = todolistService.delete(id);
    return toModel(todolist);
  }

  public EntityModel<Todolist> toModel(Todolist todolist) {
    EntityModel<Todolist> todolistModel = EntityModel.of(todolist);

    Link selfLink = linkTo(methodOn(TodolistController.class).getTodolist()).withSelfRel();
    Link addTask = linkTo(methodOn(TodolistController.class).addTask("toto")).withRel("add-task")
      .andAffordance(afford(methodOn(TodolistController.class).modifyTask(1, "exemple")))
      .andAffordance(afford(methodOn(TodolistController.class).deleteTask(1)));
//      Link actions = Affordances.of(linkTo(actionOnTask).withSelfRel())
//
//          .afford(HttpMethod.POST)
//          .withOutput(Todolist.class)
//          .withName("add-task")
//
//          .andAfford(HttpMethod.PUT)
//          .withOutput(Todolist.class) //
//          .addParameters(QueryParameter.required("id")) //
//          .withName("update-task") //
//          .toLink();

      todolistModel.add(selfLink);
      todolistModel.add(addTask);

      return todolistModel;
  }
}
