package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Etantdonné;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class TodolistSpec {

    @Autowired
    private TodolistRepository todolistRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Etantdonné("aucune tâche à faire")
    public void une_todolist_vierge() {
        when(todolistRepository.get())
                .thenReturn(Optional.of(new Todolist()));
    }

    @Etantdonné("la tâche {string} à faire")
    public void la_tache_à_faire(String task) {
        Todolist todolist = new Todolist();
        todolist.add(new Task(task));

        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }

    @Etantdonné("les tâches {string} et {string} à faire")
    public void lestâchesEtÀFaire(String firstTask, String secondTask) {
        Todolist todolist = new Todolist();
        Task taskOne = new Task(firstTask);
        Task taskTwo = new Task(secondTask);

        todolist.add(taskOne);
        todolist.add(taskTwo);

        when(taskRepository.get(taskOne.id()))
                .thenReturn(taskOne);
        when(taskRepository.get(taskTwo.id()))
                .thenReturn(taskTwo);
        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }

    @Etantdonné("une tâche terminée {string}")
    public void unetâcheTerminée(String task) {
        Todolist todolist = new Todolist();
        Task completeTask = new Task(task, true);

        todolist.add(completeTask);

        when(taskRepository.get(completeTask.id()))
                .thenReturn(completeTask);
        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }
}
