package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.models.Task;
import io.github.gabbloquet.todolist.domain.models.TaskId;

import java.util.Optional;

public class InMemoryTaskRepository implements TaskRepository {


    @Override
    public Optional<Task> get(TaskId taskId) {
        return Optional.empty();
    }

    @Override
    public void save(Task task) {

    }
}
