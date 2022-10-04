package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.domain.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskState;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistEvent;
import io.github.gabbloquet.todolist.domain.todolist.openApplication.TodolistCreated;
import io.github.gabbloquet.todolist.domain.todolist.tmp.TaskAdded;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ScenarioState {

    private final TaskId TASK_ID = new TaskId();

    private final Map<String, TaskId> tasks = new HashMap<>();

    public TaskState taskState;

    private List<TaskEvent> taskHistory = new ArrayList<>();
    private List<TodolistEvent> todolistHistory = new ArrayList<>();

    public void appendToHistory(TaskEvent... eventsToAppend) {
        taskHistory.addAll(List.of(eventsToAppend));
    }

    public void appendToHistory(TodolistEvent... eventsToAppend) {
        todolistHistory.addAll(List.of(eventsToAppend));
    }

    public ScenarioState startTodolist() {
        todolistHistory.clear();

        appendToHistory(TodolistCreated.builder()
                .build());

        return this;
    }

    public ScenarioState addTask(String task) {
        TaskId taskId = new TaskId();

        appendToHistory(TaskAdded.builder()
                .taskId(taskId)
                .description(task)
                .build());

        tasks.put(task, taskId);

        return this;
    }

    public ScenarioState completeTask(String task) {

        TaskId taskId = getTaskId(task);

        appendToHistory(TaskCompleted.builder()
                .taskId(taskId)
                .build());

        return this;
    }

    public TaskId getTaskId(String task){
        return tasks.get(task);
    }
}
