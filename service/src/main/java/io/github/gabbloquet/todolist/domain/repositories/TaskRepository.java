package io.github.gabbloquet.todolist.domain.repositories;

import io.github.gabbloquet.todolist.domain.models.Task;
import io.github.gabbloquet.todolist.domain.models.TaskId;

import java.util.Optional;

public interface TaskRepository {

    Optional<Task> get(TaskId taskId);

    void save(Task task);
}
