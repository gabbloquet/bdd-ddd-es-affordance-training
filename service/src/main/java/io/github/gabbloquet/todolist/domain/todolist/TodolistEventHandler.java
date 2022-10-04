package io.github.gabbloquet.todolist.domain.todolist;

import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.completeTask.TaskCompleted;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class TodolistEventHandler {

    @NonNull
    private final TodolistUseCaseTransaction todolistUseCaseTransaction;

    @EventListener
    public void onTaskCreated(TaskCreated event) {
        todolistUseCaseTransaction.start();

        todolistUseCaseTransaction.get().apply(event);

        todolistUseCaseTransaction.commit();
    }

    @EventListener
    public void onTaskCompleted(TaskCompleted event) {
        todolistUseCaseTransaction.start();

        todolistUseCaseTransaction.get().apply(event);

        todolistUseCaseTransaction.commit();
    }
}
