package io.github.gabbloquet.todolist.task.model;

import io.github.gabbloquet.todolist.annotations.Aggregate;
import io.github.gabbloquet.todolist.task.addTask.TaskCreated;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Aggregate
@RequiredArgsConstructor
public class Task {

    @NonNull
    public final TaskId taskId;

    @NonNull
    @Getter
    private final ArrayList<TaskEvent> events;

    public Task(@NonNull TaskId taskId) {
        this.taskId = taskId;
        this.events = new ArrayList<>();
    }

    public void addEvent(TaskEvent event) {
        this.events.add(event);
    }

    public static Task from(TaskId taskId, TaskCreated event) {
        return new Task(taskId, new ArrayList<>(List.of(event)));
    }
}
