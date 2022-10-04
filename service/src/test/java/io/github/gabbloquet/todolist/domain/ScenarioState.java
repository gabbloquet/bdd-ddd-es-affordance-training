package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskState;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ScenarioState {

    private final Map<String, TaskId> tasks = new HashMap<>();

    public TaskState taskState;

    public ScenarioState addTask(String task) {
        TaskId taskId = new TaskId();
        tasks.put(task, taskId);
        return this;
    }

    public TaskId getTaskId(String task){
        return tasks.get(task);
    }
}
