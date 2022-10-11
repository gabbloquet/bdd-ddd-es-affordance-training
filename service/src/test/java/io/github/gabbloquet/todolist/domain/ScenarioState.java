package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskState;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class ScenarioState {

    private final Map<String, Task> tasks = new LinkedHashMap<>();

    public TaskState taskState;
    public Todolist todolist;

    public ScenarioState addTask(String description, boolean done) {
        TaskId taskId = new TaskId();

        Task task = new Task(taskId, description, LocalDateTime.MIN, LocalDateTime.MIN, done);
        tasks.put(description, task);

        return this;
    }

    public ScenarioState addTask(String description, String creationDateTime, boolean done) {
        TaskId taskId = new TaskId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(creationDateTime, formatter);

        Task task = new Task(taskId, description, dateTime, null, done);
        tasks.put(description, task);

        return this;
    }

    public ScenarioState addTask(String description, String creationString, String doneString, boolean done) {
        TaskId taskId = new TaskId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
        LocalDateTime creationDateTime = LocalDateTime.parse(creationString, formatter);
        LocalDateTime doneDateTime = LocalDateTime.parse(doneString, formatter);

        Task task = new Task(taskId, description, creationDateTime, doneDateTime, done);
        tasks.put(description, task);

        return this;
    }

    public TaskId getTaskId(String task){
        return tasks.get(task).taskId();
    }

    public record Task(TaskId taskId, String description, LocalDateTime creationTime, LocalDateTime doneTime, boolean done){}
}
