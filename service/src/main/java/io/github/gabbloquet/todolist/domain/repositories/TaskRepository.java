package io.github.gabbloquet.todolist.domain.repositories;

import io.github.gabbloquet.todolist.domain.models.Task;
import io.github.gabbloquet.todolist.domain.models.TaskId;

public interface TaskRepository {

    Task get(TaskId taskId);

    void save(Task task);
}
