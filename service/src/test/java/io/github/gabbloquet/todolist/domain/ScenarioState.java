package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskState;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistEvent;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistState;
import io.github.gabbloquet.todolist.domain.todolist.openApplication.TodolistCreated;
import io.github.gabbloquet.todolist.domain.todolist.tmp.TaskAdded;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScenarioState {

    private static final TaskId TASK_ID = new TaskId();

    public String scenarioName;

    public Throwable useCaseException;
    public TaskState taskState;
    public TodolistState todolistState;

    private List<TaskEvent> taskHistory = new ArrayList<>();
    private List<TodolistEvent> todolistHistory = new ArrayList<>();

    public void appendToHistory(TaskEvent... eventsToAppend) {
        taskHistory.addAll(List.of(eventsToAppend));
    }

    public void appendToHistory(TodolistEvent... eventsToAppend) {
        todolistHistory.addAll(List.of(eventsToAppend));
    }

    public ScenarioState startTask() {
        taskHistory.clear();

        appendToHistory(TaskCreated.builder()
                .taskId(TASK_ID)
                .build());

        return this;
    }

    public ScenarioState startTodolist() {
        todolistHistory.clear();

        appendToHistory(TodolistCreated.builder()
                .build());

        return this;
    }


    public ScenarioState addDescription(String task) {
        appendToHistory(TaskCreated.builder()
                .taskId(TASK_ID)
                .description(task)
                .build());

        return this;
    }

    public ScenarioState addTask(String task) {
        appendToHistory(TaskAdded.builder()
                .taskId(new TaskId())
                .description(task)
                .build());

        return this;
    }

    public ScenarioState completeTask(String task) {
// TODO: try to find a way to do it in a clean way
        //        todolistHistory.

        return this;
    }
}
