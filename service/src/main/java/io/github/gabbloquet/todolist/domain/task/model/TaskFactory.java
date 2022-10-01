package io.github.gabbloquet.todolist.domain.task.model;

import io.github.gabbloquet.todolist.annotations.DomainFactory;
import lombok.RequiredArgsConstructor;

@DomainFactory
@RequiredArgsConstructor
public class TaskFactory {

    public Task create(TaskId taskId) {
        return new Task(taskId);
    }

}
