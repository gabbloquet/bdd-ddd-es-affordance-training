package io.github.gabbloquet.todolist.domain.OutPort;

import io.github.gabbloquet.todolist.domain.model.Task;

import java.util.Optional;

public interface TaskRepository {

    Optional<Task> get(int id);

    Optional<Task> modify(int id, String update);

    void delete(int id);
}
