package io.github.gabbloquet.bddtraining.infrastructure.api;

import io.github.gabbloquet.bddtraining.domain.Todolist;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TodolistController.BASE_PATH)
public class TodolistController {
    static final String BASE_PATH = "todolist";
    private final TodolistRepresentationService todolistRepresentationService;

    public TodolistController(TodolistRepresentationService todolistRepresentationService) {
        this.todolistRepresentationService = todolistRepresentationService;
    }

    @GetMapping
    public ResponseEntity<EntityModel<Todolist>> getTodolist() {
        var todolist = todolistRepresentationService.getTodolistModel();
        return ResponseEntity.ok(todolist);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Todolist>> addTask(@RequestBody String task) {
        var todolist = todolistRepresentationService.addTaskModel(task);
        return ResponseEntity.ok(todolist);
    }
}
