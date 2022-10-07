package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskState;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class ScenarioState {

    private final Map<String, Todolist.Task> tasks = new LinkedHashMap<>();

    public TaskState taskState;

    public ScenarioState addTask(String task, boolean done) {
        TaskId taskId = new TaskId();

        Todolist.Task todolistTask = new Todolist.Task(taskId, task, done);
        tasks.put(task, todolistTask);

        return this;
    }

    public TaskId getTaskId(String task){
        return tasks.get(task).taskId();
    }
}
