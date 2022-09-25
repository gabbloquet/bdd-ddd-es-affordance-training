package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.TaskId;
import io.github.gabbloquet.todolist.domain.model.Todolist;

import java.util.Optional;

public class InMemoryTaskRepository implements TaskRepository {


    @Override
    public Task get(TaskId taskId) {
        return null;
    }

    @Override
    public void save(Task task) {

    }
}
