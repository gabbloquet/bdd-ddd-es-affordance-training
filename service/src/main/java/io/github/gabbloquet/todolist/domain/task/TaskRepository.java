package io.github.gabbloquet.todolist.domain.task;

import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;

import java.util.Optional;

public interface TaskRepository {

    Optional<Task> get(TaskId taskId);

    void save(Task task);
}
