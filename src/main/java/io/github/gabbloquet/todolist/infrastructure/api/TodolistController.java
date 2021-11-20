package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.Todolist;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TodolistController {
    private final TodolistModelService todolistResourceService;

    public TodolistController(TodolistModelService todolistResourceService) {
        this.todolistResourceService = todolistResourceService;
    }

    @GetMapping("/todolist")
    public ResponseEntity<EntityModel<Todolist>> getTodolist() {
        var todolist = todolistResourceService.getTodolist();
        return ok(todolist);
    }

    @PostMapping("/todolist" )
    public ResponseEntity<EntityModel<Todolist>> addTask(@RequestBody String task) {
        var todolist = todolistResourceService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(todolist);
    }

    record UpdateTask(String task, String update){}
    @PutMapping("/todolist")
    public ResponseEntity<EntityModel<Todolist>> modifyTask(@RequestBody UpdateTask update) {
        var todolist = todolistResourceService.modifyTask(update.task, update.update);
        return ok(todolist);
    }
}
