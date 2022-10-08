package io.github.gabbloquet.todolist.domain.todolist;

import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.domain.task.deleteTask.TaskDeleted;
import io.github.gabbloquet.todolist.domain.task.renameTask.TaskRenamed;
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
    }

    @EventListener
    public void onTaskCompleted(TaskCompleted event) {
        todolistUseCaseTransaction.start();

        todolistUseCaseTransaction.get().apply(event);
    }

    @EventListener
    public void onTaskCompleted(TaskRenamed event) {
        todolistUseCaseTransaction.start();

        todolistUseCaseTransaction.get().apply(event);
    }

    @EventListener
    public void onTaskDeleted(TaskDeleted event) {
//        TODO: pourquoi l'event est déjà dans le repository ?
        todolistUseCaseTransaction.start();
    }
}
