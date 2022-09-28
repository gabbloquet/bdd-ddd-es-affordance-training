package io.github.gabbloquet.todolist.domain.models.error;

public class TaskNotFound extends RuntimeException {
    public TaskNotFound(int taskId) {
        super(String.format("Task %s not found.", taskId));
    }

    public TaskNotFound(String taskDescription) {
        super(String.format("Task '%s' not found.", taskDescription));
    }
}
