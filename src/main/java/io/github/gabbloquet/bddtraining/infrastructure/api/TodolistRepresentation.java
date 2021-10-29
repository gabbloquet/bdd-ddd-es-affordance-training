package io.github.gabbloquet.bddtraining.infrastructure.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class TodolistRepresentation extends RepresentationModel<TodolistRepresentation> {
    private final List<String> tasks;

    @JsonCreator
    public TodolistRepresentation(List<String> tasks) {
        this.tasks = tasks;
    }
}
