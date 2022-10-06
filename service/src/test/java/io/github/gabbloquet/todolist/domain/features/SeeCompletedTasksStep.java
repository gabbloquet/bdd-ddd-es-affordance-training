package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.todolist.TodolistService;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.PrioritizeTask;
import org.springframework.beans.factory.annotation.Autowired;

public class SeeCompletedTasksStep {

    @Autowired
    private TodolistService todolistService;

    @Autowired
    private TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Lorsque("la todolist est filtrée par tâches accomplies")
    public void laTodolistEstFiltréeParLesTâchesAccomplies() {
        todolistUseCaseTransaction.start();

        PrioritizeTask command = FilterTodolist.builder()
                        .build();

        todolistService.execute(command);
    }
}
