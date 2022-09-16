package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.model.Task;

public class InMemoryTaskRepository implements TaskRepository {


    @Override
    public Task get(String id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Task modify(int id, String update) {
        return null;
    }
}
