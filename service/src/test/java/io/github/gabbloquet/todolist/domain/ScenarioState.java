package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskState;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class ScenarioState {

    private final Map<String, Task> tasks = new LinkedHashMap<>();

    public TaskState taskState;

    public ScenarioState addTask(String description, boolean done) {
        TaskId taskId = new TaskId();

        Task task = new Task(taskId, description, done);
        tasks.put(description, task);

        return this;
    }

    public ScenarioState addTask(String description, LocalDateTime creationDate, boolean done) {
        TaskId taskId = new TaskId();

        Task task = new Task(taskId, description, creationDate, done);
        tasks.put(description, task);

        return this;
    }

    public TaskId getTaskId(String task){
        return tasks.get(task).taskId();
    }

    public record Task(TaskId taskId, String description, LocalDateTime creationTime, boolean done){

        public Task(TaskId taskId, String descritpion, boolean done) {
            this(taskId, descritpion, LocalDateTime.now(), done);
        }
    }
}
