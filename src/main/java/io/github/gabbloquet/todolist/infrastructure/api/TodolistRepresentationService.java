package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.Todolist;
import org.springframework.hateoas.EntityModel;

public interface TodolistRepresentationService {

    EntityModel<Todolist> getTodolistModel();

    EntityModel<Todolist> addTaskModel(String task);
}
