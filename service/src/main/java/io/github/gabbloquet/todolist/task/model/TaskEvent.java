package io.github.gabbloquet.todolist.task.model;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.task.deleteTask.TaskDeleted;
import io.github.gabbloquet.todolist.task.renameTask.TaskRenamed;

@DomainEvent
public interface TaskEvent {

    <T> T accept(TaskEvent.Visitor<T> visitor);

    interface Visitor<T> {
        T apply(TaskCompleted event);

        T apply(TaskCreated event);

        T apply(TaskRenamed event);

        T apply(TaskDeleted event);
    }

}

