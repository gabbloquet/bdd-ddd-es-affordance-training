package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.utils.ArrayUtils;

import java.util.List;

public record Todolist(List<String> tasks) {

    public void add(String task) {
        tasks.add(task);
    }

    public void add(List<String> tasksToAdd) {
        tasks.addAll(tasksToAdd);
    }

    public void clear() {
        tasks.clear();
    }

    public void delete(String task) {
        tasks.remove(task);
    }

    public void modify(String taskToModify, String update) {
        int indexOfTask = tasks.indexOf(taskToModify);
        tasks.set(indexOfTask, update);
    }

    public void move(String task, int position) {
        ArrayUtils.moveToPosition(tasks, task, position);
    }
}
