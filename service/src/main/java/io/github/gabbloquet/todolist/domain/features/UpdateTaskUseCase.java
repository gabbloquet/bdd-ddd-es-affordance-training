package io.github.gabbloquet.todolist.domain.features;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.InPort.commands.ModifyTask;
import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UpdateTaskUseCase {

    @NonNull
    private TaskRepository taskRepository;

    private final Todolist todolist;


    public void execute(ModifyTask modifyTask) {
        Task task = taskRepository.get(modifyTask.taskId());
        TaskUpdated taskUpdated = task.modify(modifyTask.update());
        taskRepository.save(task);

        todolist.apply(taskUpdated);
    }
}
