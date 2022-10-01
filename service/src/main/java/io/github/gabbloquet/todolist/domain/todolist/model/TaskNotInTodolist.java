package io.github.gabbloquet.todolist.domain.todolist.model;

import java.util.UUID;

public class TaskNotInTodolist  extends RuntimeException {
    public TaskNotInTodolist(UUID taskId) {
        super(String.format("Task %s not in the todolist.", taskId.toString()));
    }
}
