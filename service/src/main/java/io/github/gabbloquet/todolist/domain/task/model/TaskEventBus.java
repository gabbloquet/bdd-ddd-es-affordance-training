package io.github.gabbloquet.todolist.domain.task.model;

public interface TaskEventBus {
    void publish(TaskEvent event);
}
