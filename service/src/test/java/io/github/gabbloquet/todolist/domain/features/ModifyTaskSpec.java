package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.InPort.commands.ModifyTask;
import io.github.gabbloquet.todolist.domain.model.TaskId;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.springframework.beans.factory.annotation.Autowired;

public class ModifyTaskSpec {

    @Autowired
    private Todolist todolist;

    private UpdateTaskUseCase updateTaskUseCase;

    @Lorsque("la tâche {string} est modifée en {string}")
    public void laTâcheEstModiféeEn(String existingTask, String update) {
        TaskId taskId = todolist.findByName(existingTask);
        ModifyTask modifyTask = new ModifyTask(taskId, update);
        updateTaskUseCase.execute(modifyTask);
    }
}
