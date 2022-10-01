package io.github.gabbloquet.todolist.domain.task;

import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskNotFound;
import lombok.NonNull;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import java.util.function.Supplier;

public class TaskUseCaseTransaction implements Supplier<Task> {

    @NonNull
    private final TaskRepository taskRepository;

    private Task task;

    public TaskUseCaseTransaction(@NonNull TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void start(TaskId taskId) {
        this.task = taskRepository.get(taskId)
                .orElseThrow(() -> new TaskNotFound(taskId));
    }

    @Override
    public Task get() {
        return task;
    }

    public void commit() {
        taskRepository.save(task);
    }

    @EventListener
    @Order(1)
    public void onTaskCreation(TaskCreated event) {
        this.task = event.task;
    }
}
