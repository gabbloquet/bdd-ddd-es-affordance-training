package io.github.gabbloquet.todolist.task;

import io.github.gabbloquet.todolist.task.model.Task;
import io.github.gabbloquet.todolist.task.model.TaskId;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> get();

    Optional<Task> get(TaskId taskId);

    void save(Task task);
}
