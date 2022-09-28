package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsque;
import io.cucumber.java.fr.Quand;
import io.github.gabbloquet.todolist.domain.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.commands.OpenApplication;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.function.Supplier;

import static org.mockito.Mockito.*;

public class CreateTodolistSpec {

    @Autowired
    private TodolistService todolistService;

    @Autowired
    private TodolistRepository todolistRepository;

    @Autowired
    private TodolistUseCaseTransaction todolistUseCaseTransaction;

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
