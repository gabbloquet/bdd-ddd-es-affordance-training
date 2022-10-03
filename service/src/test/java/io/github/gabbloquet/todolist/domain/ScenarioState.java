package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskState;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistEvent;
import io.github.gabbloquet.todolist.domain.todolist.openApplication.TodolistCreated;
import io.github.gabbloquet.todolist.domain.todolist.tmp.TaskAdded;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScenarioState {

    private static final TaskId TASK_ID = new TaskId();

    public Todolist todolist;
    public TaskState taskState;

    private List<TaskEvent> taskHistory = new ArrayList<>();
    private List<TodolistEvent> todolistHistory = new ArrayList<>();

    public void appendToHistory(TaskEvent... eventsToAppend) {
        taskHistory.addAll(List.of(eventsToAppend));
    }

    public void appendToHistory(TodolistEvent... eventsToAppend) {
        todolistHistory.addAll(List.of(eventsToAppend));
    }

//    TODO: check things to delete
    public ScenarioState startTask() {
        taskHistory.clear();

        appendToHistory(TaskCreated.builder()
                .taskId(TASK_ID)
                .build());

        return this;
    }

    public ScenarioState startTodolist() {
        todolistHistory.clear();
        todolist = new Todolist();

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
        TaskId taskId = new TaskId();
        appendToHistory(TaskAdded.builder()
                .taskId(taskId)
                .description(task)
                .build());

        todolist.add(new Todolist.Task(taskId, task, false));

        return this;
    }

    public ScenarioState completeTask(String task) {
        TaskId taskId = getTask(task);

        appendToHistory(TaskCompleted.builder()
                .taskId(taskId)
                .build());

        return this;
    }

    public TaskId getTask(String task) {
        return todolist.findByName(task).taskId();
    }
}
