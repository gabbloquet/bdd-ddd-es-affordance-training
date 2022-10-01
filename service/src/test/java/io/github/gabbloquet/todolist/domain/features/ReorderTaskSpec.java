package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.todolist.TodolistService;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask.DeprioritizeTask;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.PrioritizeTask;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;

public class ReorderTaskSpec {

    @Autowired
    private TodolistService todolistService;

    @Autowired
    private TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Lorsque("la tâche {string} est prorisée")
    public void laTâcheEstProrisée(String tacheAPrioriser) {
        todolistUseCaseTransaction.start();

        Task taskToPriorize = todolistUseCaseTransaction.get().findByName(tacheAPrioriser);

        PrioritizeTask command = PrioritizeTask
                .builder()
                .taskId(taskToPriorize.id())
                .build();
        todolistService.execute(command);
    }

    @Lorsque("la tâche {string} est dépriorisée")
    public void laTâcheEstDépriorisée(String tacheADeprioriser) {
        todolistUseCaseTransaction.start();

        Task taskToDepriorize = todolistUseCaseTransaction.get().findByName(tacheADeprioriser);

        DeprioritizeTask command = DeprioritizeTask.builder()
                .taskId(taskToDepriorize.id())
                .build();
        todolistService.execute(command);
    }
}
