package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.Todolist;
import org.springframework.hateoas.EntityModel;

public interface TodolistModelService {

    EntityModel<Todolist> getTodolist();

    EntityModel<Todolist> addTask(String task);

    EntityModel<Todolist> modifyTask(String task, String update);
}
