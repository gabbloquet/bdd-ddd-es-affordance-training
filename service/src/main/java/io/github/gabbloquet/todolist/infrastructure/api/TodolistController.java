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
        EntityModel<Todolist> todolist = todolistResourceService.getTodolist();
        return ok(todolist);
    }

    @PostMapping("/todolist/tasks" )
    public ResponseEntity<EntityModel<Todolist>> addTask(@RequestBody String task) {
        EntityModel<Todolist> todolist = todolistResourceService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(todolist);
    }

    @PutMapping("/todolist/tasks/{id}")
    public ResponseEntity<EntityModel<Todolist>> modifyTask(@PathVariable int id, @RequestBody String update) {
        EntityModel<Todolist> todolist = todolistResourceService.modifyTask(id, update);
        return ok(todolist);
    }

    @DeleteMapping("/todolist/tasks/{id}")
    public ResponseEntity<EntityModel<Todolist>> deleteTask(@PathVariable int id) {
        EntityModel<Todolist> todolist = todolistResourceService.deleteTask(id);
        return ok(todolist);
    }
}
