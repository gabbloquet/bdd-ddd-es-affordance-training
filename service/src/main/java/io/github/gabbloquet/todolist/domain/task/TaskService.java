package io.github.gabbloquet.todolist.domain.task;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.task.addTask.OpenTask;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandBus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@DomainService
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    @NonNull
    private final TaskRepository taskRepository;

    @NonNull
    private final TodolistCommandBus todolistCommandBus;

    public Task execute(OpenTask command) {
        log.info("[{}] command={}",
                command.getClass().getSimpleName(),
                command);

        todolistCommandBus.dispatch(command);

        return taskUseCaseTransaction.get();
    }


    public Task execute(TaskCommand command) {
        log.info("[{}] command={}",
                command.getClass().getSimpleName(),
                command);

        taskUseCaseTransaction.start(command.taskId);

        todolistCommandBus.dispatch(command);

        taskUseCaseTransaction.commit();

//        TODO: maintenant on doit jouer les evenements pour les appliquer à la todolist

        return taskUseCaseTransaction.get();
    }

    public Task getTask(UUID id) {
        return new Task(new TaskId(id));
    }
}
