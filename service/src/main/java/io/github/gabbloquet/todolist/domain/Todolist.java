package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.utils.ArrayUtils;

import java.util.List;

public record Todolist(List<Task> tasks) {

    public void add(String task) {
        tasks.add(new Task(tasks.size(), task));
    }

    public void clear() {
        tasks.clear();
    }

    public void delete(int id) {
        tasks.remove(tasks.get(id));
    }

    public void modify(int id, String update) {
        tasks.set(id, new Task(id, update));
    }

    public void move(int id, int position) {
        ArrayUtils.moveToPosition(tasks, tasks.get(id), position);
    }
}
