package io.github.gabbloquet.todolist.domain.features;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Etantdonné("les tâches à faire")
    public void les_taches_a_faire(List<String> tasks) {
        scenarioState
                .addTask(tasks.get(0), false)
                .addTask(tasks.get(1), false);

        mockTasks();
    }

    @Etantdonné("les tâches terminées")
    public void les_taches_terminees(List<String> tasks) {
        scenarioState
                .addTask(tasks.get(0), true)
                .addTask(tasks.get(1), true);

        mockTasks();
    }

    //    @Etantdonné("les tâches à faire")
//    public void les_taches_a_faire(Set<Task> tasks) {
//        tasks.forEach(task -> {
//            scenarioState.addTask(task.taskId, task., false);
//            mockTaskToDo(row.get(0), LocalDateTime.parse(row.get(1)));
//        });
//
//        mockTodolist();
//    }

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

    @Alors("aucune tâche n'est proposée")
    public void aucune_tache_proposée() {
        Assertions.assertEquals(0, todolistUseCaseTransaction.get().render().size());
    }

    private void mockTasks() {
        List<Task> taskAggregates = scenarioState.getTasks().values().stream()
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

        taskAggregates
                .forEach((task) -> {
                    when(taskRepository.get(task.taskId))
                            .thenReturn(Optional.of(task));
                });

        when(taskRepository.get())
                .thenReturn(taskAggregates);
    }

//    private void mockTaskToDo(String description, LocalDateTime dateTime) {
//        TaskId taskId = scenarioState.getTaskId(description);
//
//        when(taskRepository.get(taskId))
//                .thenReturn(Optional.of(new Task(taskId, new ArrayList<>(List.of(
//                        TaskCreated.builder()
//                                .taskId(taskId)
//                                .description(description)
//                                .creationTime(dateTime)
//                                .isCompleted(false)
//                                .build()
//                )))));
//    }
}
