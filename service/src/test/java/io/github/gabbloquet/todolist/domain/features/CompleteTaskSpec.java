package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.InPort.commands.CompleteTask;
import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.TaskId;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;

public class CompleteTaskSpec {

    @Autowired
    private Todolist todolist;

    @Autowired
    private TodolistService todolistService;

    @Autowired
    private TaskRepository taskRepository;

    @Etantdonné("les tâches {string} et {string} à faire")
    public void lestâchesEtÀFaire(String firstTask, String secondTask) {
        Task taskOne = new Task(firstTask);
        Task taskTwo = new Task(secondTask);

        todolist.add(taskOne);
        todolist.add(taskTwo);

        when(taskRepository.get(taskOne.id()))
                .thenReturn(taskOne);
        when(taskRepository.get(taskTwo.id()))
                .thenReturn(taskTwo);
    }

    @Etantdonné("une tâche terminée {string}")
    public void unetâcheTerminée(String task) {
        Task completeTask = new Task(task, true);

        todolist.add(completeTask);

        when(taskRepository.get(completeTask.id()))
                .thenReturn(completeTask);
    }

    @Lorsque("la tâche {string} est accomplie")
    public void latâcheEstAccomplie(String task) {
        TaskId taskToCompleteId = todolist.findByName(task);
        CompleteTask command = new CompleteTask(taskToCompleteId);

        todolistService.completeTask(command);
    }

    @Alors("la tâche {string} est terminée")
    public void latâcheEstTerminée(String task) {
        TaskId completedTaskId = todolist.findByName(task);
        Task completedTask = todolist.tasks().get(completedTaskId);

        Assertions.assertTrue(completedTask.isCompleted());
    }

    @Et("la tâche {string} est placée en haut de la liste")
    public void latâcheEstPlacéeEnHautDeLaListe(String task) {
        TaskId firstTaskId = todolist.findByName(task);

        Assertions.assertEquals(todolist.render().get(0).id(), firstTaskId);
    }

    @Et("la tâche {string} est placée en seconde position de la liste")
    public void latâcheEstPlacéeEnSecondePositionDeLaListe(String task) {
        TaskId secondTaskId = todolist.findByName(task);

        Assertions.assertEquals(todolist.render().get(1).id(), secondTaskId);
    }
}
