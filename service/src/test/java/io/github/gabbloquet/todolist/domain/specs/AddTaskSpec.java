package io.github.gabbloquet.todolist.domain.specs;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.task.TaskService;
import io.github.gabbloquet.todolist.task.addTask.AddTask;
import org.springframework.beans.factory.annotation.Autowired;

public class AddTaskSpec {

    @Autowired
    private ScenarioState scenarioState;

    @Autowired
    private TaskService taskService;

    @Lorsque("la tâche {string} est ajoutée")
    public void la_tâche_est_ajoutee(String task) {
        scenarioState.taskState = taskService.execute(
            AddTask
                .builder()
                .description(task)
                .build()
        );
    }
}
