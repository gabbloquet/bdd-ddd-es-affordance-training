package io.github.gabbloquet.todolist.todolist;

import io.github.gabbloquet.todolist.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.task.deleteTask.TaskDeleted;
import io.github.gabbloquet.todolist.task.renameTask.TaskRenamed;
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
    public void onTaskRenamed(TaskRenamed event) {
        todolistUseCaseTransaction.start();

        todolistUseCaseTransaction.get().apply(event);
    }

    @EventListener
    public void onTaskDeleted(TaskDeleted event) {
//        TODO: pourquoi l'event est déjà dans le repository ?
        todolistUseCaseTransaction.start();
    }
}
