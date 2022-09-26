package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Quand;
import io.github.gabbloquet.todolist.domain.commands.StartTodolist;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

public class CreateTodolistSpec {

    @Autowired
    private TodolistService todolistService;

    @Autowired
    private TodolistRepository todolistRepository;

    @Autowired
    private Todolist todolist;

    private final ArgumentCaptor<Todolist> todolistArgumentCaptor = ArgumentCaptor.forClass(Todolist.class);

    @Quand("l'application est ouverte")
    public void lApplicationEstOuverte() {
        todolistService.execute(new StartTodolist());
    }

    @Alors("une todolist vierge est disponible")
    public void uneTodolistViergeEstDisponible() {
        verify(todolistRepository)
                .save(todolistArgumentCaptor.capture());

        Assertions.assertTrue(todolistArgumentCaptor.getValue().tasks().isEmpty());
        Assertions.assertEquals(todolistArgumentCaptor.getValue(), todolist);
    }
}
