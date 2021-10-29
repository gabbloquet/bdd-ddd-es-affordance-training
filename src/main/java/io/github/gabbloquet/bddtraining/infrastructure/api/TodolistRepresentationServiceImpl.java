package io.github.gabbloquet.bddtraining.infrastructure.api;

import io.github.gabbloquet.bddtraining.domain.InPort.TodolistService;
import io.github.gabbloquet.bddtraining.domain.Todolist;
import org.springframework.stereotype.Service;

@Service
public class TodolistRepresentationServiceImpl implements TodolistRepresentationService {

    private final TodolistService todolistService;

    public TodolistRepresentationServiceImpl(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    @Override
    public TodolistRepresentation getTodolistRepresentation() {
        Todolist todolist = todolistService.getTodolist();
        return new TodolistRepresentation(todolist.tasks());
    }
}
