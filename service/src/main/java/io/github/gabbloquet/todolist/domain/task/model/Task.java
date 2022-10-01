package io.github.gabbloquet.todolist.domain.task.model;

import io.github.gabbloquet.todolist.annotations.Aggregate;
import io.github.gabbloquet.todolist.domain.task.completeTask.CompleteTask;
import io.github.gabbloquet.todolist.domain.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.domain.task.modifyTask.TaskModified;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Aggregate
@RequiredArgsConstructor
public class Task {

    @NonNull
    public final TaskId taskId;

    @NonNull
    private final ArrayList<TaskEvent> events;

    public Task(@NonNull TaskId taskId) {
        this.taskId = taskId;
        this.events = new ArrayList<>();
    }
}
