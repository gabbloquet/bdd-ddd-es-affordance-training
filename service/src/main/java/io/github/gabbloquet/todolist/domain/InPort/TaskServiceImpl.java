package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @NonNull
    private final TaskRepository taskRepository;
    @NonNull
    private final TodolistRepository todolistRepository;

    @Override
    public Task get(int id) {
        return taskRepository.get(id);
    }

    public Task add(String task) {
        var todolist = todolistRepository.get();

        Task taskToAdd = new Task(task);
        todolist.add(taskToAdd);

        todolistRepository.save(todolist);

        return taskToAdd;
    }

    public void delete(int id) {
        taskRepository.delete(id);
    }

    public Task modify(int id, String update) {
        return taskRepository.modify(id, update);
    }
}
