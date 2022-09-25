package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.InPort.commands.CreateTask;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AddTaskSpec {

    @Autowired
    private TodolistService todolistService;

    @Autowired
    private TodolistRepository todolistRepository;

    @Autowired
    private Todolist todolist;

    @Etantdonné("aucune tâche à faire")
    public void une_todolist_vierge() {
        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }

    @Etantdonné("la tâche {string} à faire")
    public void la_tache_à_faire(String task) {
        todolist.add(new Task(task));

        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }


    @Lorsque("la tâche {string} est ajoutée")
    public void la_tâche_est_ajoutee(String task) {
        todolistService.addTask(new CreateTask(task));
    }


    @Alors("la tâche {string} est à faire")
    public void la_todolist_contient_une_tache(String expectedTask) {
        Assertions.assertEquals(todolist.render().get(0).description(), expectedTask);
        Assertions.assertFalse(todolist.render().get(0).isCompleted());

        verify(todolistRepository, times(1)).save(todolist);
    }

    @Alors("les tâches {string} et {string} sont à faire")
    public void la_todolist_contient_deux_tâches(String firstTask, String secondTask) {
        Assertions.assertEquals(todolist.render().get(0).description(), firstTask);
        Assertions.assertFalse(todolist.render().get(0).isCompleted());

        Assertions.assertEquals(todolist.render().get(1).description(), secondTask);
        Assertions.assertFalse(todolist.render().get(1).isCompleted());

        verify(todolistRepository, times(1)).save(todolist);
    }
}
