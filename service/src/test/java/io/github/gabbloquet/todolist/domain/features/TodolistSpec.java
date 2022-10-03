package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Etantdonné;
import io.github.gabbloquet.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.domain.task.TaskRepository;
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

        when(todolistRepository.get())
                .thenReturn(Optional.of(new Todolist(new ArrayList(List.of(new Task(UUID.randomUUID(), task, false))))));

//        Todolist todolist = new Todolist();
//
//        Task taskToDo = new Task(task);
//        todolist.add(taskToDo);
//
//        when(todolistRepository.get())
//                .thenReturn(Optional.of(todolist));
    }

    @Etantdonné("les tâches {string} et {string} à faire")
    public void lestâchesEtÀFaire(String firstTask, String secondTask) {
        scenarioState.startTodolist()
                .addTask(firstTask)
                .addTask(secondTask);

//        Todolist todolist = new Todolist();
//        Task taskOne = new Task(firstTask);
//        Task taskTwo = new Task(secondTask);
//
//        todolist.add(taskOne);
//        todolist.add(taskTwo);
//
//        when(taskRepository.get(taskOne.id()))
//                .thenReturn(Optional.of(taskOne));
//        when(taskRepository.get(taskTwo.id()))
//                .thenReturn(Optional.of(taskTwo));
//        when(todolistRepository.get())
//                .thenReturn(Optional.of(todolist));
    }

    @Etantdonné("une tâche terminée {string}")
    public void unetâcheTerminée(String task) {
        scenarioState.startTodolist()
                .addTask(task)
                .completeTask(task);

//        Todolist todolist = new Todolist();
//        Task completeTask = new Task(task, true);
//
//        todolist.add(completeTask);
//
//        when(taskRepository.get(completeTask.id()))
//                .thenReturn(Optional.of(completeTask));
//        when(todolistRepository.get())
//                .thenReturn(Optional.of(todolist));
    }
}
