package io.github.gabbloquet.todolist.domain.model;

import io.github.gabbloquet.todolist.utils.ArrayUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Todolist {

    private List<Task> tasks;

    public Todolist() {
        this(new ArrayList<>());
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void clear() {
        tasks.clear();
    }

    public void delete(Task task) {
        tasks.remove(task);
    }

    public void modify(Task task, String update) {
        int position = tasks.indexOf(task);
        tasks.set(position, new Task(update));
    }

    public void move(Task task, int position) {
        ArrayUtils.moveToPosition(tasks, task, position);
    }
}
