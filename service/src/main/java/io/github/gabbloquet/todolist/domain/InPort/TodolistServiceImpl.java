package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.application.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.InPort.commands.CompleteTask;
import io.github.gabbloquet.todolist.domain.InPort.commands.CreateTask;
import io.github.gabbloquet.todolist.domain.features.TaskCompleted;
import io.github.gabbloquet.todolist.domain.features.TaskCreated;
import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TodolistServiceImpl implements TodolistService {

    @NonNull
    private final TaskRepository taskRepository;
    private final Todolist todolist;

    @Override
    public void addTask(CreateTask command) {
        Task task = new Task(command.description());
        taskRepository.save(task);

        todolist.apply(new TaskCreated(task));
    }

    @Override
    public void completeTask(CompleteTask command) {
        Task task = taskRepository.get(command.id());
        TaskCompleted taskCompleted = task.complete();
        taskRepository.save(task);

        todolist.apply(taskCompleted);
    }

    @Override
    public void openTodolist() {
        todolistRepository.save(new Todolist());
    }
}
