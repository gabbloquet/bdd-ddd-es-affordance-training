package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.domain.task.TaskService;
import io.github.gabbloquet.todolist.domain.task.addTask.AddTask;
import io.github.gabbloquet.todolist.domain.todolist.TodolistRepository;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddTaskSpec {

    @Autowired
    private ScenarioState scenarioState;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TodolistRepository todolistRepository;

    @Autowired
    private TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Lorsque("la tâche {string} est ajoutée")
    public void la_tâche_est_ajoutee(String task) {
        scenarioState.taskState = taskService.execute(
            AddTask
                .builder()
                .description(task)
                .build()
        );
    }

    @Alors("la tâche {string} est à faire")
    public void la_todolist_contient_une_tache(String expectedTask) {
//        TODO: checker event créé 1 fois
        assertThat(scenarioState.taskState.getDescription()).isEqualTo(expectedTask);
        assertFalse(scenarioState.taskState.isCompleted());

        Assertions.assertEquals(todolistUseCaseTransaction.get().tasks().get(0).name(), expectedTask);
        Assertions.assertFalse(todolistUseCaseTransaction.get().tasks().get(0).done());

        verify(todolistRepository, times(1)).save(todolistUseCaseTransaction.get());
    }

    @Alors("les tâches {string} et {string} sont à faire")
    public void la_todolist_contient_deux_tâches(String firstTask, String secondTask) {
        Assertions.assertEquals(todolistUseCaseTransaction.get().tasks().get(0).name(), firstTask);
        Assertions.assertFalse(todolistUseCaseTransaction.get().tasks().get(0).done());

        Assertions.assertEquals(todolistUseCaseTransaction.get().tasks().get(1).name(), secondTask);
        Assertions.assertFalse(todolistUseCaseTransaction.get().tasks().get(1).done());

        verify(todolistRepository, times(1)).save(todolistUseCaseTransaction.get());
    }
}
