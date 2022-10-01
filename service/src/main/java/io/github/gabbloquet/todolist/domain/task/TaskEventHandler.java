package io.github.gabbloquet.todolist.domain.task;

import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class TaskEventHandler {

    @NonNull
    private final TaskFactory taskFactory;

    @NonNull
    private final TaskRepository taskRepository;


    @EventListener()
    public void taskCreated(TaskCreated taskCreated) {
        Task task = taskFactory.create(taskCreated.taskId);
        taskRepository.save(task);
    }
}
