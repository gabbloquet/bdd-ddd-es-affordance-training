package io.github.gabbloquet.bddtraining.infrastructure.api;

import io.github.gabbloquet.bddtraining.domain.Todolist;
import org.springframework.hateoas.EntityModel;

public interface TodolistRepresentationService {

    EntityModel<Todolist> getTodolistModel();

    EntityModel<Todolist> addTaskModel(String task);
}
