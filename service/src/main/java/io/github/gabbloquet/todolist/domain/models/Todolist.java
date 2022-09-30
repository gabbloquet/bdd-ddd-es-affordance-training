package io.github.gabbloquet.todolist.domain.models;

import io.github.gabbloquet.todolist.application.annotations.Aggregate;
import io.github.gabbloquet.todolist.domain.features.events.*;
import io.github.gabbloquet.todolist.domain.models.error.TaskNotFound;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Aggregate
public class Todolist {

    private final ArrayList<Task> tasks;
    private final ArrayList<Task> completedTasks;
    private final List<TaskEvent> unsavedEvents;

    public Todolist() {
        this.tasks = new ArrayList<>();
        this.completedTasks = new ArrayList<>();
        this.unsavedEvents = new ArrayList<>();
    }

    public Todolist(ArrayList<Task> taskToAdd) {
        this.tasks = taskToAdd;
        this.completedTasks = new ArrayList<>();
        this.unsavedEvents = new ArrayList<>();
    }

    public ArrayList<Task> tasks() {
        return tasks;
    }

    public TaskPrioritized prioritize(@NonNull TaskId taskId) {
        Task prioritizeTask = this.findById(taskId);
        List<Task> tasksWithoutPrioritize = new ArrayList<>(this.tasks)
                .stream()
                .filter(task -> task.id() != taskId)
                .toList();

        this.tasks.clear();

        this.tasks.add(prioritizeTask);
        this.tasks.addAll(tasksWithoutPrioritize);

        return TaskPrioritized.builder().build();
    }

    public TaskDeprioritized deprioritize(@NonNull TaskId taskId) {
        Task deprioritizeTask = this.findById(taskId);
        List<Task> tasksWithoutPrioritize = new ArrayList<>(this.tasks)
                .stream()
                .filter(task -> task.id() != taskId)
                .toList();

        this.tasks.clear();

        this.tasks.addAll(tasksWithoutPrioritize);
        this.tasks.add(deprioritizeTask);

        return TaskDeprioritized.builder().build();
    }

    private Task findById(TaskId taskId) {
        return this.tasks.stream()
                .filter(task -> task.id().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new TaskNotFound(taskId));
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

    public void apply(TaskModified taskModified) {
        int taskPosition = getTaskPosition(taskModified);
        tasks.set(taskPosition, taskModified.getTask());
    }

    public void apply(TaskCreated taskCreated) {
        tasks.add(taskCreated.task);
    }

    public void apply(TaskCompleted taskCompleted) {
        completedTasks.add(taskCompleted.getTask());
    }

    private int getTaskPosition(TaskModified taskModified) {
        return IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).id().equals(taskModified.getTask().id()))
                .findFirst()
                .orElseThrow(() -> new TaskNotFound(taskModified.getTask().description()));
    }

    public void add(Task task) {
        if(task.isCompleted())
            completedTasks.add(task);
        else
            tasks.add(task);
    }

    public void addUnsavedEvent(TaskEvent event) {
        this.unsavedEvents.add(event);
    }

    public List<TaskEvent> unsavedEvents() {
//        return this.unsavedEvents.forEach(taskEvent -> {
//            if(taskEvent instanceof ){
//
//            }
//        });
        return List.of();
    }
}
