package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.commands.AddTask;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddTaskSpec {

    @Autowired
    private TodolistService todolistService;

    @Autowired
    private TodolistRepository todolistRepository;

    @Autowired
    private TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Lorsque("la tâche {string} est ajoutée")
    public void la_tâche_est_ajoutee(String task) {
        todolistUseCaseTransaction.start();
        todolistService.execute(
            AddTask
                .builder()
                .description(task)
                .build()
        );
    }


    @Alors("la tâche {string} est à faire")
    public void la_todolist_contient_une_tache(String expectedTask) {
        Assertions.assertEquals(todolistUseCaseTransaction.get().render().get(0).description(), expectedTask);
        Assertions.assertFalse(todolistUseCaseTransaction.get().render().get(0).isCompleted());

        verify(todolistRepository, times(1)).save(todolistUseCaseTransaction.get());
    }

    @Alors("les tâches {string} et {string} sont à faire")
    public void la_todolist_contient_deux_tâches(String firstTask, String secondTask) {
        Todolist todolist = todolistUseCaseTransaction.get();

        Assertions.assertEquals(todolist.render().get(0).description(), firstTask);
        Assertions.assertFalse(todolist.render().get(0).isCompleted());

        Assertions.assertEquals(todolist.render().get(1).description(), secondTask);
        Assertions.assertFalse(todolist.render().get(1).isCompleted());

        verify(todolistRepository, times(1)).save(todolist);
    }
}
