package io.github.gabbloquet.todolist.domain.todolist.model;

import io.github.gabbloquet.todolist.annotations.Aggregate;
import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskNotFound;
import io.github.gabbloquet.todolist.domain.task.modifyTask.TaskModified;
import io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask.TaskDeprioritized;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.TaskPrioritized;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

    public TaskPrioritized prioritize(@NonNull UUID taskId) {
        Task prioritizeTask = this.findById(taskId);
        List<Task> tasksWithoutPrioritize = new ArrayList<>(this.tasks)
                .stream()
                .filter(task -> task.id != taskId)
                .toList();

        this.tasks.clear();

        this.tasks.add(prioritizeTask);
        this.tasks.addAll(tasksWithoutPrioritize);

        return TaskPrioritized.builder().build();
    }

    public TaskDeprioritized deprioritize(@NonNull UUID taskId) {
        Task deprioritizeTask = this.findById(taskId);
        List<Task> tasksWithoutPrioritize = new ArrayList<>(this.tasks)
                .stream()
                .filter(task -> task.id != taskId)
                .toList();

        this.tasks.clear();

        this.tasks.addAll(tasksWithoutPrioritize);
        this.tasks.add(deprioritizeTask);

        return TaskDeprioritized.builder().build();
    }

    private Task findById(UUID taskId) {
        return this.tasks.stream()
                .filter(task -> task.id.equals(taskId))
                .findFirst()
                .orElseThrow(() -> new TaskNotInTodolist(taskId));
    }

    public Task findByName(String taskToFound) {
        return this.tasks.stream()
                .filter(task -> task.description.equals(taskToFound))
                .findFirst()
                .orElseThrow(() -> new TaskNotFound(taskToFound));
    }

    public List<Task> render() {
        return Stream.concat(completedTasks.stream(), tasks.stream()).toList();
    }

//    public void apply(TaskModified taskModified) {
//        int taskPosition = getTaskPosition(taskModified);
//        tasks.set(taskPosition, taskModified.getTask());
//    }
//
//    public void apply(TaskCreated taskCreated) {
//        tasks.add(taskCreated.task);
//    }
//
//    public void apply(TaskCompleted taskCompleted) {
//        completedTasks.add(taskCompleted.getTask());
//    }

//    private int getTaskPosition(TaskModified taskModified) {
//        return IntStream.range(0, tasks.size())
//                .filter(i -> tasks.get(i).id.equals(taskModified.getTask().id))
//                .findFirst()
//                .orElseThrow(() -> new TaskNotFound(taskModified.getTask().description));
//    }

    public void add(Task task) {
        if(task.isCompleted)
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

    class Task {

        public UUID id;
        public String description;

        public boolean isCompleted;
    }
}
