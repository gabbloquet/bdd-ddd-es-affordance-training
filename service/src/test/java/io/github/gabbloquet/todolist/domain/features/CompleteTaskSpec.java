package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.domain.task.TaskService;
import io.github.gabbloquet.todolist.domain.task.completeTask.CompleteTask;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist.Task;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompleteTaskSpec {

    @Autowired
    private ScenarioState scenarioState;

    @Autowired
    private TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Autowired
    private TaskService taskService;

    @Lorsque("la tâche {string} est accomplie")
    public void latâcheEstAccomplie(String task) {

        TaskId taskId = scenarioState.getTaskId(task);
        CompleteTask command = CompleteTask.builder()
                .taskId(taskId)
                .build();

        scenarioState.taskState = taskService.execute(command);
    }

    @Alors("la tâche {string} est terminée")
    public void latâcheEstTerminée(String task) {
        assertThat(scenarioState.taskState.getDescription()).isEqualTo(task);
        assertTrue(scenarioState.taskState.isCompleted());

        Task completedTask = todolistUseCaseTransaction.get().findByName(task);
        Assertions.assertTrue(completedTask.done());
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

        Assertions.assertEquals(todolist.render().get(1).name(), secondTask.name());
    }
}
