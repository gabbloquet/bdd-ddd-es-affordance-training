package io.github.gabbloquet.todolist.infrastructure.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public class TodolistModel extends CollectionModel<TodolistModel> {
    private final List<String> tasks;

    @JsonCreator
    public TodolistModel(List<String> tasks) {
        this.tasks = tasks;
    }
}
