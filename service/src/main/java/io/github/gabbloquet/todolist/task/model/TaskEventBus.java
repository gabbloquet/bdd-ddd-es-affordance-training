package io.github.gabbloquet.todolist.task.model;

public interface TaskEventBus {
    void publish(TaskEvent event);
}
