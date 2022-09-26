package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.commands.DeprioritizeTask;
import io.github.gabbloquet.todolist.domain.commands.PrioritizeTask;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.springframework.beans.factory.annotation.Autowired;

public class ReorderTaskSpec {

    @Autowired
    private PriorizeTaskUseCase priorizeTaskUseCase;

    @Autowired
    private DeprioritizeTaskUseCase deprioritizeTaskUseCase;

    @Autowired
    private Todolist todolist;

    @Lorsque("la tâche {string} est prorisée")
    public void laTâcheEstProrisée(String tacheAPrioriser) {
        Task taskToPriorize = todolist.findByName(tacheAPrioriser);

        PrioritizeTask command = new PrioritizeTask(taskToPriorize);
        priorizeTaskUseCase.execute(command);
    }

    @Lorsque("la tâche {string} est dépriorisée")
    public void laTâcheEstDépriorisée(String tacheADeprioriser) {
        Task taskToDepriorize = todolist.findByName(tacheADeprioriser);

        DeprioritizeTask command = new DeprioritizeTask(taskToDepriorize);
        deprioritizeTaskUseCase.execute(command);
    }
}
