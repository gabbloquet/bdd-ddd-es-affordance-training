package io.github.gabbloquet.todolist.application.services;

import io.github.gabbloquet.todolist.domain.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistController;
import io.github.gabbloquet.todolist.application.utils.SimpleIdentifiableRepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class TodolistRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<Todolist> {

    /**
     * Link the {@link Todolist} domain type to the {@link TodolistController} using this
     * {@link SimpleIdentifiableRepresentationModelAssembler} in order to generate both
     * {@link org.springframework.hateoas.EntityModel} and {@link org.springframework.hateoas.CollectionModel}.
     */
    TodolistRepresentationModelAssembler() {
        super(TodolistController.class);
    }
}