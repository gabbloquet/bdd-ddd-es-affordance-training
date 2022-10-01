package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.todolist.TodolistService;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.task.modifyTask.ModifyTask;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;

public class ModifyTaskSpec {

    @Autowired
    private TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Autowired
    private TodolistService todolistService;

    @Lorsque("la tâche {string} est modifée en {string}")
    public void laTâcheEstModiféeEn(String existingTask, String update) {
        todolistUseCaseTransaction.start();

        Task task = todolistUseCaseTransaction.get().findByName(existingTask);
        ModifyTask modifyTask = ModifyTask.builder()
                .taskId(task.id())
                .update(update)
                .build();

        todolistService.execute(modifyTask);
    }
}
