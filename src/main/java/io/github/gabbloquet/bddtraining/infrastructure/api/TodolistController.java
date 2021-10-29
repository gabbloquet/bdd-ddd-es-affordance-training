package io.github.gabbloquet.bddtraining.infrastructure.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TodolistController.BASE_PATH)
public class TodolistController {
    static final String BASE_PATH = "todolist";
    private final TodolistRepresentationService todolistRepresentationService;

    public TodolistController(TodolistRepresentationService todolistRepresentationService) {
        this.todolistRepresentationService = todolistRepresentationService;
    }

    @GetMapping
    public HttpEntity<TodolistRepresentation> getTodolist() {
        TodolistRepresentation todolist = todolistRepresentationService.getTodolistRepresentation();
        return new ResponseEntity<>(todolist, HttpStatus.OK);
    }
}
