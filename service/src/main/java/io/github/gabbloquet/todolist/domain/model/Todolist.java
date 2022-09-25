package io.github.gabbloquet.todolist.domain.model;

import io.github.gabbloquet.todolist.application.annotations.Aggregate;
import io.github.gabbloquet.todolist.domain.InPort.commands.OpenTodolist;
import io.github.gabbloquet.todolist.domain.features.events.TaskCompleted;
import io.github.gabbloquet.todolist.domain.features.events.TaskCreated;
import io.github.gabbloquet.todolist.domain.features.events.TaskPriorized;
import io.github.gabbloquet.todolist.domain.features.events.TaskUpdated;
import io.github.gabbloquet.todolist.infrastructure.spi.TaskNotFound;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Aggregate
public class Todolist {

    private ArrayList<Task> tasks;
    private ArrayList<Task> completedTasks;

    public Todolist() {
        this.tasks = new ArrayList<>();
        this.completedTasks = new ArrayList<>();
    }

    public Todolist(ArrayList<Task> taskToAdd) {
        this.tasks = taskToAdd;
    }

    public ArrayList<Task> tasks() {
        return tasks;
    }

    public TaskPriorized priorize(Task task) {
        ArrayList<Task> existingTasks = new ArrayList<>(this.tasks);
        existingTasks.remove(task);

        this.tasks.clear();

        this.tasks.add(task);
        this.tasks.addAll(existingTasks);

        return new TaskPriorized();
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
        tasks.set(taskPosition, taskUpdated.task());
    }

    public void apply(TaskCreated taskCreated) {
        tasks.add(taskCreated.task());
    }

    public void apply(TaskCompleted taskCompleted) {
        completedTasks.add(taskCompleted.task());
    }

    public void apply(OpenTodolist command) {}

    private int getTaskPosition(TaskUpdated taskUpdated) {
        return IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).id().equals(taskUpdated.task().id()))
                .findFirst()
                .orElseThrow(() -> new TaskNotFound(taskUpdated.task().description()));
    }

    public void add(Task task) {
        if(task.isCompleted())
            completedTasks.add(task);
        else
            tasks.add(task);
    }
}
