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

    @Etantdonné("une todolist contenant la tache {task}")
    public void toto(Task task) {
        todolist = new Todolist();
        todolist.add(task);

        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }


    @Lorsque("la tache {task} est ajoutée")
    public void la_tache_est_ajoutee(Task task) {
        todolist = todolistService.addTask(task);
    }


    @Alors("la todolist contient {task}")
    public void la_todolist_contient(Task expectedTask) {
        Assertions.assertEquals(todolist.tasks().get(0), expectedTask);
        verify(todolistRepository, times(1)).save(todolist);
    }

    @Alors("la todolist contient {task} et {task}")
    public void la_todolist_contient_les_deux_taches(Task firstTask, Task secondTask) {
        Assertions.assertEquals(todolist.tasks().get(0), firstTask);
        Assertions.assertEquals(todolist.tasks().get(1), secondTask);

        verify(todolistRepository, times(1)).save(todolist);
    }
}
