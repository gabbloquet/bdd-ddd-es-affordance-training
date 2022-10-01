package io.github.gabbloquet.todolist.domain.task.model;

import io.github.gabbloquet.todolist.annotations.DomainEvent;
import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.domain.task.modifyTask.TaskModified;

@DomainEvent
public interface TaskEvent {

    <T> T accept(TaskEvent.Visitor<T> visitor);

    interface Visitor<T> {
        T apply(TaskCompleted event);

        T apply(TaskCreated event);

        T apply(TaskModified event);
    }

}

