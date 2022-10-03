package io.github.gabbloquet.todolist.domain.task;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.task.addTask.OpenTask;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskState;
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
    private final TaskUseCaseTransaction taskUseCaseTransaction;

    @NonNull
    private final TodolistCommandBus todolistCommandBus;

    public TaskState execute(OpenTask command) {
        log.info("[{}] command={}",
                command.getClass().getSimpleName(),
                command);

        todolistCommandBus.dispatch(command);
        taskUseCaseTransaction.commit();

        return new TaskState(taskUseCaseTransaction.get().getEvents());
    }


    public TaskState execute(TaskCommand command) {
        log.info("[{}] command={}",
                command.getClass().getSimpleName(),
                command);

        taskUseCaseTransaction.start(command.taskId);

        todolistCommandBus.dispatch(command);

        taskUseCaseTransaction.commit();

        return new TaskState(taskUseCaseTransaction.get().getEvents());
    }

    public TaskState getTask(UUID id) {
        taskUseCaseTransaction.start(new TaskId(id));

        return new TaskState(taskUseCaseTransaction.get().getEvents());
    }
}
