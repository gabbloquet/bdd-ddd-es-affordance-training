package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.TaskId;

public class InMemoryTaskRepository implements TaskRepository {


    @Override
    public Task get(TaskId taskId) {
        return null;
    }

    @Override
    public void save(Task task) {

    }
}
