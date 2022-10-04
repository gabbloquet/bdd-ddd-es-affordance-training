package io.github.gabbloquet.todolist.domain.task.infra.dto;

import io.github.gabbloquet.todolist.domain.task.model.TaskState;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist.Task;

import java.util.UUID;

public record TaskDto(UUID id, String description, boolean isCompleted) {
    public static TaskDto from(Task task) {
        return new TaskDto(task.taskId().id(), task.name(), task.done());
    }

    public static TaskDto from(TaskState task) {
        return new TaskDto(task.getId().id(), task.getDescription(), task.isCompleted());
    }
}
