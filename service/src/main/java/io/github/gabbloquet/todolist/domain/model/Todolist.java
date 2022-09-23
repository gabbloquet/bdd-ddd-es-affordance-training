package io.github.gabbloquet.todolist.domain.model;

import io.github.gabbloquet.todolist.application.annotations.Aggregate;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Aggregate
public class Todolist {

    private List<Task> tasks;

    public Todolist() {
        this.tasks = new ArrayList<>();
    }

    public Todolist(List<Task> taskToAdd) {
        this.tasks = taskToAdd;
    }

    public List<Task> tasks() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void modify(Task taskToUpdate) {
        tasks = tasks.stream().map(task -> {
            if(Objects.equals(task.id(), taskToUpdate.id())){
                return taskToUpdate;
            }
            return task;
        })
        .toList();
    }

    public Task getTask(Task taskToFound) {
        Optional<Task> foundTask = tasks.stream()
                .filter(task -> Objects.equals(task.id(), taskToFound.id()))
                .findFirst();

        return foundTask
                .orElseThrow();
    }

    public void completeTask(Task task) {
        Task taskToComplete = getTask(task);
        taskToComplete.complete();
        modify(taskToComplete);
    }
}
