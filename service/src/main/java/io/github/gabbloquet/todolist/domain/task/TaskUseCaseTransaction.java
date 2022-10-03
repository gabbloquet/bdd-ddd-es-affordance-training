package io.github.gabbloquet.todolist.domain.task;

import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;
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
        task = this.taskRepository.get(taskId)
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
    public void onTaskEvent(TaskEvent event) {
        task.addEvent(event);
    }

    @EventListener
    @Order(0)
    public void onTaskStarted(TaskCreated event) {
        task = Task.from(event.taskId, event);
    }

}
