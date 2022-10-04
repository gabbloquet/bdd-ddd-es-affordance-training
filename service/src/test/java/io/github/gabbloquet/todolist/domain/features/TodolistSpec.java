package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Etantdonné;
import io.github.gabbloquet.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.domain.task.TaskRepository;
import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.todolist.TodolistRepository;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        mockTodolist(new Todolist());
    }

    @Etantdonné("la tâche {string} à faire")
    public void la_tache_à_faire(String task) {
        scenarioState.startTodolist()
                .addTask(task);

        TaskId taskId = scenarioState.getTaskId(task);
        ArrayList<Todolist.Task> tasks = new ArrayList<>(List.of(new Todolist.Task(taskId, task, false)));
        Todolist existingTodolist = new Todolist(tasks);

        mockTaskToDo(task);
        mockTodolist(existingTodolist);
    }

    @Etantdonné("les tâches {string} et {string} à faire")
    public void lestâchesEtÀFaire(String firstTask, String secondTask) {
        scenarioState.startTodolist()
                .addTask(firstTask)
                .addTask(secondTask);

        TaskId firstTaskId = scenarioState.getTaskId(firstTask);
        TaskId secondTaskId = scenarioState.getTaskId(secondTask);
        ArrayList<Todolist.Task> tasks = new ArrayList<>(List.of(
                new Todolist.Task(firstTaskId, firstTask, false),
                new Todolist.Task(secondTaskId, secondTask, false)
        ));
        Todolist existingTodolist = new Todolist(tasks);

        mockTaskToDo(firstTask);
        mockTaskToDo(secondTask);
        mockTodolist(existingTodolist);
    }

    @Etantdonné("une tâche terminée {string}")
    public void unetâcheTerminée(String task) {
        scenarioState.startTodolist()
                .addTask(task)
                .completeTask(task);

        TaskId taskId = scenarioState.getTaskId(task);
        ArrayList<Todolist.Task> tasks = new ArrayList<>(List.of(new Todolist.Task(taskId, task, true)));
        Todolist existingTodolist = new Todolist(tasks);

        mockTaskCompleted(task);
        mockTodolist(existingTodolist);
    }

    private void mockTaskToDo(String description) {
        TaskId taskId = scenarioState.getTaskId(description);

        when(taskRepository.get(taskId))
                .thenReturn(Optional.of(new Task(taskId, new ArrayList<>(List.of(
                        TaskCreated.builder()
                                .taskId(taskId)
                                .description(description)
                                .isCompleted(false)
                                .build()
                )))));
    }

    private void mockTaskCompleted(String description) {
        TaskId taskId = scenarioState.getTaskId(description);

        when(taskRepository.get(taskId))
                .thenReturn(Optional.of(new Task(taskId, new ArrayList<>(List.of(
                        TaskCreated.builder()
                                .taskId(taskId)
                                .description(description)
                                .isCompleted(true)
                                .build()
                )))));
    }

    private void mockTodolist(Todolist todolist) {
        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }
}
