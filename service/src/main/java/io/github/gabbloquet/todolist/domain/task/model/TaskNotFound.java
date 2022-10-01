package io.github.gabbloquet.todolist.domain.task.model;

public class TaskNotFound extends RuntimeException {
    public TaskNotFound(TaskId taskId) {
        super(String.format("Task %s not found.", taskId));
    }

    public TaskNotFound(String taskDescription) {
        super(String.format("Task '%s' not found.", taskDescription));
    }
}
