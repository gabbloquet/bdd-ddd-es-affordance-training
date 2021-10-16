package io.github.gabbloquet.bddtraining.infrastructure.api;

import io.github.gabbloquet.bddtraining.domain.InPort.TodolistService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TodolistController.BASE_PATH)
public class TodolistController {
    static final String BASE_PATH = "todolist";
    private TodolistService todolistService;
}
