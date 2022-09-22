package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AddTaskSpec {

    private Todolist todolist;

    @Autowired
    private TodolistService todolistService;

    @Autowired
    private TodolistRepository todolistRepository;

    @Etantdonné("une todolist vierge")
    public void une_todolist_vierge() {
        todolist = new Todolist();

        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }

    @Etantdonné("une todolist contenant la tache {string}")
    public void toto(String task) {
        todolist = new Todolist();
        todolist.add(new Task(task));

        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }


    @Lorsque("la tache {string} est ajoutée")
    public void la_tache_est_ajoutee(String task) {
        todolist = todolistService.addTask(new Task(task));
    }


    @Alors("la todolist contient {string}")
    public void la_todolist_contient(String expectedTask) {
        Assertions.assertEquals(todolist.tasks().get(0), new Task(expectedTask));
        verify(todolistRepository, times(1)).save(todolist);
    }

    @Alors("la todolist contient {string} et {string}")
    public void la_todolist_contient_les_deux_taches(String firstTask, String secondTask) {
        Assertions.assertEquals(todolist.tasks().get(0), new Task(firstTask));
        Assertions.assertEquals(todolist.tasks().get(1), new Task(secondTask));

        verify(todolistRepository, times(1)).save(todolist);
    }
}
