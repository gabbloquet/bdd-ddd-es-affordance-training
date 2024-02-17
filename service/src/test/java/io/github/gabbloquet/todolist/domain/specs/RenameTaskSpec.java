package io.github.gabbloquet.todolist.domain.specs;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.task.TaskService;
import io.github.gabbloquet.todolist.task.model.TaskId;
import io.github.gabbloquet.todolist.task.renameTask.RenameTask;
import org.springframework.beans.factory.annotation.Autowired;

public class RenameTaskSpec {

    @Autowired
    private ScenarioState scenarioState;

    @Autowired
    private TaskService taskService;

    @Lorsque("la tâche {string} est renommée {string}")
    public void la_tache_est_renommee(String existingTask, String update) {

        TaskId taskId = scenarioState.getTaskId(existingTask);
        RenameTask command = RenameTask.builder()
                .taskId(taskId)
                .update(update)
                .build();

        scenarioState.taskState = taskService.execute(command);
    }
}
