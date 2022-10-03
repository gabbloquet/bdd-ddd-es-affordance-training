package io.github.gabbloquet.todolist.domain.todolist.model;

import io.github.gabbloquet.todolist.domain.task.model.TaskId;

public class TaskNotInTodolist  extends RuntimeException {
    public TaskNotInTodolist(TaskId taskId) {
        super(String.format("Task %s not in the todolist.", taskId.toString()));
    }
}
