package io.github.gabbloquet.todolist.domain.OutPort;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.TaskId;

public interface TaskRepository {

    Task get(TaskId taskId);

    void save(Task task);
}
