package io.github.gabbloquet.features.todolist.domain.specs;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.features.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.task.TaskService;
import io.github.gabbloquet.todolist.task.deleteTask.DeleteTask;
import io.github.gabbloquet.todolist.task.model.TaskId;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteTaskSpec {

    @Autowired
    private ScenarioState scenarioState;
    @Autowired
    private TaskService taskService;

    @Lorsque("la tâche {string} est supprimée")
    public void la_tache_est_supprimee(String task) {
        TaskId taskId = scenarioState.getTaskId(task);
        DeleteTask command = DeleteTask.builder()
                .taskId(taskId)
                .build();

        scenarioState.taskState = taskService.execute(command);
    }
}
