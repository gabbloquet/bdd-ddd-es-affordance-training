package io.github.gabbloquet.bddtraining.application.services;

import io.github.gabbloquet.bddtraining.domain.InPort.TodolistService;
import io.github.gabbloquet.bddtraining.domain.Todolist;
import io.github.gabbloquet.bddtraining.infrastructure.api.TodolistRepresentationService;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
public class TodolistRepresentationServiceImpl implements TodolistRepresentationService {

    private final TodolistRepresentationModelAssembler assembler;
    private final TodolistService todolistService;

    public TodolistRepresentationServiceImpl(TodolistRepresentationModelAssembler assembler, TodolistService todolistService) {
        this.assembler = assembler;
        this.todolistService = todolistService;
    }

    @Override
    public EntityModel<Todolist> getTodolistModel() {
        Todolist todolist = todolistService.getTodolist();
        return assembler.toModel(todolist);
    }

    @Override
    public EntityModel<Todolist> addTaskModel(String task) {
        Todolist todolist = todolistService.add(task);
        return assembler.toModel(todolist);
    }
}
