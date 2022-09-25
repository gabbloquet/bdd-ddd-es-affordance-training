package io.github.gabbloquet.todolist.domain.model;

import io.github.gabbloquet.todolist.application.annotations.Aggregate;
import io.github.gabbloquet.todolist.domain.InPort.commands.OpenTodolist;
import io.github.gabbloquet.todolist.domain.features.TaskCompleted;
import io.github.gabbloquet.todolist.domain.features.TaskCreated;
import io.github.gabbloquet.todolist.domain.features.TaskUpdated;
import io.github.gabbloquet.todolist.infrastructure.spi.TaskNotFound;

import java.util.*;

@Aggregate
public class Todolist {

    private Map<TaskId, Task> tasks;

    public Todolist() {
        this.tasks = new LinkedHashMap<>();
    }

    public Todolist(Map<TaskId, Task> taskToAdd) {
        this.tasks = taskToAdd;
    }

    public Map<TaskId, Task> tasks() {
        return tasks;
    }

    public List<Task> render() {
        return tasks.values().stream().toList();
    }

//    public void modify(Task taskToUpdate) {
//        tasks = tasks.stream().map(task -> {
//            if(Objects.equals(task.id(), taskToUpdate.id())){
//                return taskToUpdate;
//            }
//            return task;
//        })
//        .toList();
//    }

//    public Task getTask(Task taskToFound) {
//        Optional<Task> foundTask = tasks.values().stream()
//                .filter(task -> Objects.equals(task.id(), taskToFound.id()))
//                .findFirst();
//
//        return foundTask
//                .orElseThrow();
//    }
//
//    public void completeTask(Task task) {
//        Task taskToComplete = getTask(task);
//        taskToComplete.complete();
//        modify(taskToComplete);
//    }

    public TaskId findByName(String taskToFound) {
        return this.tasks.values().stream()
                .filter(task -> task.description().equals(taskToFound))
                .findFirst()
                .map(Task::id)
                .orElseThrow(() -> new TaskNotFound(taskToFound));
    }

    public void apply(TaskUpdated taskUpdated) {
        add(taskUpdated.task());
    }

    public void apply(TaskCreated taskCreated) {
        add(taskCreated.task());
    }

    public void apply(TaskCompleted taskCompleted) {
        add(taskCompleted.task());
    }

    public void add(Task task) {
        tasks.put(task.id(), task);
    }

    public void apply(OpenTodolist command) {
        tasks = new LinkedHashMap<>();
    }
}
