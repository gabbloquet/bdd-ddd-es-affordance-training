package io.github.gabbloquet.todolist.domain.task;

import io.github.gabbloquet.todolist.annotations.DomainService;
import io.github.gabbloquet.todolist.domain.task.addTask.StartTask;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandBus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DomainService
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    @NonNull
    private final TaskUseCaseTransaction taskUseCaseTransaction;

    @NonNull
    private final TodolistCommandBus todolistCommandBus;

    public Task execute(StartTask command) {
        log.info("[{}] command={}",
                command.getClass().getSimpleName(),
                command);

        todolistCommandBus.dispatch(command);
        taskUseCaseTransaction.commit();

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
}
