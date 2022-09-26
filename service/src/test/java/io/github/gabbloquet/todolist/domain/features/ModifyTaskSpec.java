package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Lorsque;
import io.github.gabbloquet.todolist.domain.commands.ModifyTask;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.springframework.beans.factory.annotation.Autowired;

public class ModifyTaskSpec {

    @Autowired
    private Todolist todolist;

    @Autowired
    private UpdateTaskUseCase updateTaskUseCase;

    @Lorsque("la tâche {string} est modifée en {string}")
    public void laTâcheEstModiféeEn(String existingTask, String update) {
        Task task = todolist.findByName(existingTask);
        ModifyTask modifyTask = new ModifyTask(task.id(), update);

        updateTaskUseCase.execute(modifyTask);
    }
}
