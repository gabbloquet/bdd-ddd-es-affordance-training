package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.todolist.openApplication.OpenApplication;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.TodolistRepository;
import io.github.gabbloquet.todolist.domain.todolist.TodolistService;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.verify;

public class CreateTodolistSpec {

    @Autowired
    private TodolistService todolistService;

    @Autowired
    private TodolistRepository todolistRepository;

    private final ArgumentCaptor<Todolist> todolistArgumentCaptor = ArgumentCaptor.forClass(Todolist.class);

    @Lorsque("l'application est ouverte")
    public void lApplicationEstOuverte() {
        todolistService.execute(new OpenApplication());
    }

    @Alors("une todolist vierge est disponible")
    public void uneTodolistViergeEstDisponible() {
        verify(todolistRepository)
                .save(todolistArgumentCaptor.capture());

        Assertions.assertTrue(todolistArgumentCaptor.getValue().tasks().isEmpty());
    }
}
