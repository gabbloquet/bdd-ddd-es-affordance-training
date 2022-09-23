package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class CompleteTaskSpec {

    private Todolist todolist;

    @Autowired
    private TodolistService todolistService;

    @Autowired
    private TodolistRepository todolistRepository;

    @Etantdonné("les taches {string} et {string} à faire")
    public void lesTachesEtÀFaire(String firstTask, String secondTask) {
        todolist = new Todolist();

        todolist.add(new Task(firstTask));
        todolist.add(new Task(secondTask));

        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }

    @Etantdonné("une tache terminée {string}")
    public void uneTacheTerminée(String task) {
        todolist = new Todolist();

        Task completeTask = new Task(task, true);
        todolist.add(completeTask);

        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }

    @Lorsque("la tache {string} est accomplie")
    public void laTacheEstAccomplie(String task) {
        todolist = todolistService.completeTask(new Task(task));
    }

    @Alors("la tache {string} est terminée")
    public void laTacheEstTerminée(String task) {
        Task completedTask = todolist.getTask(new Task(task));

        Assertions.assertTrue(completedTask.isCompleted());
    }

    @Et("la tache {string} est placée en haut de la liste")
    public void laTacheEstPlacéeEnHautDeLaListe(String task) {
        Task firstTask = todolist.getTask(new Task(task));

        Assertions.assertEquals(todolist.tasks().get(0), firstTask);
    }

    @Et("la tache {string} est placée en seconde position de la liste")
    public void laTacheEstPlacéeEnSecondePositionDeLaListe(String task) {
        Task secondTask = todolist.getTask(new Task(task));

        Assertions.assertEquals(todolist.tasks().get(1), secondTask);
    }
}
