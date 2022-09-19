package io.github.gabbloquet.todolist.domain.OutPort;

import io.github.gabbloquet.todolist.domain.model.Task;

public interface TaskRepository {

    Task get(int id);

    void delete(int id);

    Task modify(int id, String update);
}
