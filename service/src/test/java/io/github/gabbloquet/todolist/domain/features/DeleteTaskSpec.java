package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.domain.task.TaskService;
import io.github.gabbloquet.todolist.domain.task.deleteTask.DeleteTask;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
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
