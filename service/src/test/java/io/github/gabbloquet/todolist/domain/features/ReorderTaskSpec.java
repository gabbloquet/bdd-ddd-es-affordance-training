package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.todolist.TodolistService;
import io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask.DeprioritizeTask;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.PrioritizeTask;
import org.springframework.beans.factory.annotation.Autowired;

public class ReorderTaskSpec {

    @Autowired
    private TodolistService todolistService;

    @Autowired
    private ScenarioState scenarioState;

    @Lorsque("la tâche {string} est prorisée")
    public void laTâcheEstProrisée(String tacheAPrioriser) {
        TaskId taskId = scenarioState.getTaskId(tacheAPrioriser);

        PrioritizeTask command = PrioritizeTask
                .builder()
                .taskId(taskId)
                .build();

        todolistService.execute(command);
    }

    @Lorsque("la tâche {string} est dépriorisée")
    public void laTâcheEstDépriorisée(String tacheADeprioriser) {
        TaskId taskId = scenarioState.getTaskId(tacheADeprioriser);

        DeprioritizeTask command = DeprioritizeTask.builder()
                .taskId(taskId)
                .build();

        todolistService.execute(command);
    }
}
