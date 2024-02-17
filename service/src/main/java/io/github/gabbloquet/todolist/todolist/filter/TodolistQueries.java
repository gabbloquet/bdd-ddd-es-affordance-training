package io.github.gabbloquet.todolist.todolist.filter;

import io.github.gabbloquet.todolist.todolist.model.Todolist;

public interface TodolistQueries {

    Todolist filterBy(Filter completedTasks);

    enum Filter {
        COMPLETED_TASKS,
        TO_DO_TASKS
    }
}
