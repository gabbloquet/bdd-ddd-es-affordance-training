package io.github.gabbloquet.todolist.domain.todolist.model;

import io.github.gabbloquet.todolist.annotations.Projection;
import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.domain.task.deleteTask.TaskDeleted;
import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskNotFound;
import io.github.gabbloquet.todolist.domain.task.renameTask.TaskRenamed;
import io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask.TaskDeprioritized;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.TaskPrioritized;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Projection
@EqualsAndHashCode
public class Todolist implements TaskEvent.Visitor<Todolist> {

    private final ArrayList<Task> tasks;
    private final ArrayList<Task> completedTasks;

    public Todolist() {
        this.tasks = new ArrayList<>();
        this.completedTasks = new ArrayList<>();
    }

    public Todolist(ArrayList<Task> tasksToAdd) {
        this.tasks = new ArrayList<>();
        this.completedTasks = new ArrayList<>();
        tasksToAdd.forEach(this::add);
    }

    public TaskPrioritized prioritize(@NonNull TaskId taskId) {
        Task taskToPrioritize = this.findById(taskId);
        List<Task> tasksWithoutPrioritize = new ArrayList<>(this.tasks)
                .stream()
                .filter(task -> task.taskId != taskId)
                .toList();

        this.tasks.clear();

        this.tasks.add(taskToPrioritize);
        this.tasks.addAll(tasksWithoutPrioritize);

        return TaskPrioritized.builder().build();
    }

    public TaskDeprioritized deprioritize(@NonNull TaskId taskId) {
        Task taskToDeprioritize = this.findById(taskId);
        List<Task> tasksWithoutPrioritize = new ArrayList<>(this.tasks)
                .stream()
                .filter(task -> task.taskId != taskId)
                .toList();

        this.tasks.clear();

        this.tasks.addAll(tasksWithoutPrioritize);
        this.tasks.add(taskToDeprioritize);

        return TaskDeprioritized.builder().build();
    }

    private Task findById(TaskId taskId) {
        return Stream.concat(completedTasks.stream(), tasks.stream())
                .filter(task -> task.taskId.equals(taskId))
                .findFirst()
                .orElseThrow(() -> new TaskNotInTodolist(taskId));
    }

    public Task findByName(String taskToFound) {
        return Stream.concat(completedTasks.stream(), tasks.stream())
                .filter(task -> task.name.equals(taskToFound))
                .findFirst()
                .orElseThrow(() -> new TaskNotFound(taskToFound));
    }

    public List<Task> render() {
        return Stream.concat(completedTasks.stream(), tasks.stream()).toList();
    }

    public void add(Task task) {
        if (task.done)
            completedTasks.add(task);
        else
            tasks.add(task);
    }

    public Todolist apply(TaskCreated event) {
        this.tasks.add(new Task(event.taskId, event.description, event.creationTime, Duration.ZERO, event.isCompleted));
        return this;
    }

    public Todolist apply(TaskCompleted event) {
        Task existingTask = findById(event.taskId);
        Duration duration = Duration.between(existingTask.creationDateTime, event.at);

        Task completedTask = new Task(existingTask.taskId, existingTask.name, existingTask.creationDateTime, duration,true);

        completedTasks.add(completedTask);
        tasks.remove(existingTask);

        return this;
    }

    public Todolist apply(TaskRenamed event) {
        Task existingTask = findById(event.taskId);
        int position = tasks.indexOf(existingTask);

        Task modifiedTask = new Task(existingTask.taskId, event.getDescription(), existingTask.creationDateTime, existingTask.duration, existingTask.done);

        tasks.set(position, modifiedTask);

        return this;
    }

    public Todolist apply(TaskDeleted event) {
        Task taskToDelete = findById(event.taskId);
        tasks.remove(taskToDelete);

        return this;
    }

    public Todolist apply(TaskEvent taskEvent) {
        return taskEvent.accept(this);
    }

    public record Task(TaskId taskId, String name, LocalDateTime creationDateTime, Duration duration, boolean done) {
        public Task(String task) {
            this(new TaskId(UUID.randomUUID()), task, LocalDateTime.now(), Duration.ZERO, false);
        }
    }
}
