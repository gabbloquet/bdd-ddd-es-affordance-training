package io.github.gabbloquet.bddtraining.application.services;

import io.github.gabbloquet.bddtraining.domain.Todolist;
import io.github.gabbloquet.bddtraining.infrastructure.api.TodolistController;
import io.github.gabbloquet.bddtraining.infrastructure.utils.SimpleIdentifiableRepresentationModelAssembler;
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