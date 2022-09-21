package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.spi.TaskNotFound;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @NonNull
    private final TaskRepository taskRepository;
    @NonNull
    private final TodolistRepository todolistRepository;

    @Override
    public Task get(int id) {
        return taskRepository.get(id)
                .orElseThrow(() -> new TaskNotFound(id));

    }

    public Task add(String task) {
        Optional<Todolist> todolist = todolistRepository.get();

        Task taskToAdd = new Task(task);
        todolist.ifPresent(foundTodolist -> {
            foundTodolist.add(taskToAdd);
            todolistRepository.save(foundTodolist);
        });

        return taskToAdd;
    }

    public void delete(int id) {
        taskRepository.delete(id);
    }

    public Task modify(int id, String update) {
        return taskRepository.modify(id, update)
                .orElseThrow(() -> new TaskNotFound(id));
    }
}
