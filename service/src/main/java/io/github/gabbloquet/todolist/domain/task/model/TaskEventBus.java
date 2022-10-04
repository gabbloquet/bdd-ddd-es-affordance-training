package io.github.gabbloquet.todolist.domain.task.model;

import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;

public interface TaskEventBus {
    void publish(TaskEvent event);
}
