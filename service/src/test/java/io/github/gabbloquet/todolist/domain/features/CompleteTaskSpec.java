package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.commands.CompleteTask;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Supplier;

public class CompleteTaskSpec {

    @Autowired
    private TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Autowired
    private CompleteTaskUseCase completeTaskUseCase;

    @Lorsque("la tâche {string} est accomplie")
    public void latâcheEstAccomplie(String task) {
        todolistUseCaseTransaction.start();

        Task taskToComplete = todolistUseCaseTransaction.get().findByName(task);
        CompleteTask command = new CompleteTask(taskToComplete.id());

        completeTaskUseCase.execute(command);
    }

    @Alors("la tâche {string} est terminée")
    public void latâcheEstTerminée(String task) {
        Task completedTask = todolistUseCaseTransaction.get().findByName(task);

        Assertions.assertTrue(completedTask.isCompleted());
    }

    @Et("la tâche {string} est placée en haut de la liste")
    public void latâcheEstPlacéeEnHautDeLaListe(String task) {
        Todolist todolist = todolistUseCaseTransaction.get();
        Task firstTask = todolist.findByName(task);

        Assertions.assertEquals(todolist.render().get(0), firstTask);
    }

    @Et("la tâche {string} est placée en seconde position de la liste")
    public void latâcheEstPlacéeEnSecondePositionDeLaListe(String task) {
        Todolist todolist = todolistUseCaseTransaction.get();
        Task secondTask = todolist.findByName(task);

        Assertions.assertEquals(todolist.render().get(1), secondTask);
    }
}
