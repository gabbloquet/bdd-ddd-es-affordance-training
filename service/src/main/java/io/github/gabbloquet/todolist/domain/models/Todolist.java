package io.github.gabbloquet.todolist.domain.models;

import io.github.gabbloquet.todolist.application.annotations.Aggregate;
import io.github.gabbloquet.todolist.domain.features.events.*;
import io.github.gabbloquet.todolist.domain.models.error.TaskNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Aggregate
public class Todolist {

    private final ArrayList<Task> tasks;
    private ArrayList<Task> completedTasks;
    private TodolistEvent unsavedEvent;

    public Todolist() {
        this.tasks = new ArrayList<>();
        this.completedTasks = new ArrayList<>();
        this.unsavedEvent = null;
    }

    public Todolist(ArrayList<Task> taskToAdd) {
        this.tasks = taskToAdd;
    }

    public ArrayList<Task> tasks() {
        return tasks;
    }

    public TaskPrioritized prioritize(Task task) {
        ArrayList<Task> existingTasks = new ArrayList<>(this.tasks);
        existingTasks.remove(task);

        this.tasks.clear();

        this.tasks.add(task);
        this.tasks.addAll(existingTasks);

        return TaskPrioritized.builder().build();
    }

    public TaskDeprioritized deprioritize(Task task) {
        ArrayList<Task> existingTasks = new ArrayList<>(this.tasks);
        existingTasks.remove(task);

        this.tasks.clear();

        this.tasks.addAll(existingTasks);
        this.tasks.add(task);

        return TaskDeprioritized.builder().build();
    }

    public TodolistCreated create() {
        return TodolistCreated.builder().build();
    }

    public Task findByName(String taskToFound) {
        return this.tasks.stream()
                .filter(task -> task.description().equals(taskToFound))
                .findFirst()
                .orElseThrow(() -> new TaskNotFound(taskToFound));
    }

    public List<Task> render() {
        return Stream.concat(completedTasks.stream(), tasks.stream()).toList();
    }

    public void apply(TaskUpdated taskUpdated) {
        int taskPosition = getTaskPosition(taskUpdated);
        tasks.set(taskPosition, taskUpdated.getTask());
    }

    public void apply(TaskCreated taskCreated) {
        tasks.add(taskCreated.getTask());
    }

    public void apply(TaskCompleted taskCompleted) {
        completedTasks.add(taskCompleted.getTask());
    }

    private int getTaskPosition(TaskUpdated taskUpdated) {
        return IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).id().equals(taskUpdated.getTask().id()))
                .findFirst()
                .orElseThrow(() -> new TaskNotFound(taskUpdated.getTask().description()));
    }

    public void add(Task task) {
        if(task.isCompleted())
            completedTasks.add(task);
        else
            tasks.add(task);
    }

    public void addUnsavedEvent(TodolistEvent event) {
        this.unsavedEvent = event;
    }
}
