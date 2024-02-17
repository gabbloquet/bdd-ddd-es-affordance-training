package io.github.gabbloquet.todolist.domain.specs;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.todolist.TodolistService;
import io.github.gabbloquet.todolist.todolist.openApplication.OpenApplication;
import org.springframework.beans.factory.annotation.Autowired;

public class OpenApplicationSpec {

    @Autowired
    private TodolistService todolistService;

    @Lorsque("l'application est ouverte")
    public void lApplicationEstOuverte() {
        todolistService.execute(new OpenApplication());
    }
}
