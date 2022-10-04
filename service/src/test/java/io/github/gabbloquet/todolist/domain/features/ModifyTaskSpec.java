package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.domain.task.TaskService;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.modifyTask.ModifyTask;
import org.springframework.beans.factory.annotation.Autowired;

public class ModifyTaskSpec {

    @Autowired
    private ScenarioState scenarioState;

    @Autowired
    private TaskService taskService;

    @Lorsque("la tâche {string} est modifée en {string}")
    public void laTâcheEstModiféeEn(String existingTask, String update) {

        TaskId taskId = scenarioState.getTaskId(existingTask);
        ModifyTask command = ModifyTask.builder()
                .taskId(taskId)
                .update(update)
                .build();

        scenarioState.taskState = taskService.execute(command);
    }
}
