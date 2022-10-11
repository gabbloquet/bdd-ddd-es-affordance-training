package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.github.gabbloquet.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.domain.task.TaskRepository;
import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.Mockito.*;

public class TodolistSpec {

    @Autowired
    private ScenarioState scenarioState;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Etantdonné("aucune tâche à faire")
    public void une_todolist_vierge() {
    }

    @Etantdonné("la tâche {string} à faire")
    public void la_tache_à_faire(String task) {
        scenarioState.addTask(task, false);

        mockTasks();
    }

    @Etantdonné("la tâche {string} terminée")
    public void la_tache_terminee(String task) {
        scenarioState
                .addTask(task, true);

        mockTasks();
    }

    @Etantdonné("les tâches terminées")
    public void les_taches_terminees(DataTable dataTable) {
        dataTable.entries().forEach(row -> {
            if(row.get("Créée le") != null)
                scenarioState.addTask(row.get("Description"), row.get("Créée le"), true);
            else
                scenarioState.addTask(row.get("Description"), true);
        });

        mockTasks();
    }

    @Etantdonné("les tâches à faire")
    public void les_taches_a_faire(DataTable dataTable) {
        dataTable.entries().forEach(row -> {
            if(row.get("Créée le") != null)
                scenarioState.addTask(row.get("Description"), row.get("Créée le"), false);
            else
                scenarioState.addTask(row.get("Description"), false);
        });

        mockTasks();
    }

    @Alors("la tâche {string} est à faire")
    @Alors("la tâche {string} est affichée")
    public void la_todolist_contient_une_tache(String expectedTask) {
        Assertions.assertEquals(1, todolistUseCaseTransaction.get().render().size());

        Assertions.assertEquals(expectedTask, todolistUseCaseTransaction.get().render().get(0).name());
        Assertions.assertFalse(todolistUseCaseTransaction.get().render().get(0).done());
    }

    @Alors("les tâches à faire sont")
    public void la_todolist_contient_deux_tâches(List<String> tasks) {
        Assertions.assertEquals(2, todolistUseCaseTransaction.get().render().size());

        Assertions.assertEquals(tasks.get(0), todolistUseCaseTransaction.get().render().get(0).name());
        Assertions.assertFalse(todolistUseCaseTransaction.get().render().get(0).done());

        Assertions.assertEquals(tasks.get(1), todolistUseCaseTransaction.get().render().get(1).name());
        Assertions.assertFalse(todolistUseCaseTransaction.get().render().get(1).done());
    }

    @Alors("les tâches proposées sont")
    public void les_taches_proposees_sont(DataTable dataTable) {
        List<Todolist.Task> purposedTasks = scenarioState.todolist.render();

        Assertions.assertEquals(dataTable.height() - 1, purposedTasks.size());

        AtomicInteger counter = new AtomicInteger();
        dataTable.entries().forEach((row) -> {
            if(row.get("Durée") != null)
                Assertions.assertEquals(parseDuration(row), purposedTasks.get(counter.get()).duration());
            Assertions.assertEquals(row.get("Description"), purposedTasks.get(counter.get()).name());
            counter.getAndIncrement();
        });
    }

    private Duration parseDuration(Map<String, String> row) {
        return switch (row.get("Durée")) {
            case "7 jour(s) et 3 heure(s)" -> Duration.ofDays(7).plusHours(3);
            case "3 jour(s) et 1 heure(s)" -> Duration.ofDays(3).plusHours(1);
            case "2 jour(s)" -> Duration.ofDays(2);
            case "1 jour(s) et 23 heure(s)" -> Duration.ofDays(1).plusHours(23);
            default -> Duration.ZERO;
        };
    }

    @Alors("aucune tâche n'est proposée")
    public void aucune_tache_proposée() {
        Assertions.assertEquals(0, todolistUseCaseTransaction.get().render().size());
    }

    private void mockTasks() {
        List<Task> taskAggregates = getTaskAggregatesFromScenarioStage();

        taskAggregates
                .forEach((task) -> {
                    when(taskRepository.get(task.taskId))
                            .thenReturn(Optional.of(task));
                });

        when(taskRepository.get())
                .thenReturn(taskAggregates);
    }

    private List<Task> getTaskAggregatesFromScenarioStage() {
        return scenarioState.getTasks().values().stream()
                .map((task) ->
                        new Task(task.taskId(), new ArrayList<>(List.of(
                                TaskCreated.builder()
                                        .taskId(task.taskId())
                                        .description(task.description())
                                        .creationTime(task.creationTime())
                                        .isCompleted(task.done())
                                        .build()
                        )))
                ).toList();
    }
}
