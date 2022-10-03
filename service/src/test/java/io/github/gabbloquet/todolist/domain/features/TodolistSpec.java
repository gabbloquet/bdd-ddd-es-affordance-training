package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Etantdonné;
import io.github.gabbloquet.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.domain.task.TaskRepository;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.todolist.TodolistRepository;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class TodolistSpec {

    @Autowired
    private ScenarioState scenarioState;

    @Autowired
    private TodolistRepository todolistRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Etantdonné("aucune tâche à faire")
    public void une_todolist_vierge() {
        scenarioState.startTodolist();

        when(todolistRepository.get())
                .thenReturn(Optional.of(new Todolist()));
    }

    @Etantdonné("la tâche {string} à faire")
    public void la_tache_à_faire(String task) {
        scenarioState.startTodolist()
                .addTask(task);

        ArrayList<Task> tasks = new ArrayList<>(List.of(new Task(new TaskId(), task, false)));
        Todolist existingTodolist = new Todolist(tasks);
        scenarioState.todolist = existingTodolist;

        when(todolistRepository.get())
                .thenReturn(Optional.of(existingTodolist));
    }

    @Etantdonné("les tâches {string} et {string} à faire")
    public void lestâchesEtÀFaire(String firstTask, String secondTask) {
        scenarioState.startTodolist()
                .addTask(firstTask)
                .addTask(secondTask);

        ArrayList<Task> tasks = new ArrayList<>(List.of(
                new Task(new TaskId(), firstTask, false),
                new Task(new TaskId(), secondTask, false)
        ));
        Todolist existingTodolist = new Todolist(tasks);
        scenarioState.todolist = existingTodolist;

        when(todolistRepository.get())
                .thenReturn(Optional.of(existingTodolist));
    }

    @Etantdonné("une tâche terminée {string}")
    public void unetâcheTerminée(String task) {
        scenarioState.startTodolist()
                .addTask(task)
                .completeTask(task);

        ArrayList<Task> tasks = new ArrayList<>(List.of(new Task(new TaskId(), task, true)));
        Todolist existingTodolist = new Todolist(tasks);
        scenarioState.todolist = existingTodolist;

        when(todolistRepository.get())
                .thenReturn(Optional.of(existingTodolist));
    }
}
