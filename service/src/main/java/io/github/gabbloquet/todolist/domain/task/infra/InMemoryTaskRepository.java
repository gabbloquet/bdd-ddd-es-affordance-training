package io.github.gabbloquet.todolist.domain.task.infra;

import io.github.gabbloquet.todolist.domain.task.TaskRepository;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;

import java.util.List;
import java.util.Optional;

public class InMemoryTaskRepository implements TaskRepository {


    @Override
    public List<Task> get() {
        return List.of();
    }

    @Override
    public Optional<Task> get(TaskId taskId) {
        return Optional.empty();
    }

    @Override
    public void save(Task task) {

    }
}
