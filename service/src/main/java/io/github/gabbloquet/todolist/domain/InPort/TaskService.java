package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.infrastructure.spi.TaskNotFound;

public interface TaskService {
    Task get(int id) throws TaskNotFound;

    Task add(String task);

    Task modify(int id, String update);

    void delete(int id);
}