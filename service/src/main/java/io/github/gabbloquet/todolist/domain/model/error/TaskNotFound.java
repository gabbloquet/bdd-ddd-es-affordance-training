package io.github.gabbloquet.todolist.domain.model.error;

public class TaskNotFound extends RuntimeException {
    public TaskNotFound(int taskId) {
        super(String.format("Task %s not found.", taskId));
    }

    public TaskNotFound(String taskDescription) {
        super(String.format("Task '%s' not found.", taskDescription));
    }
}
