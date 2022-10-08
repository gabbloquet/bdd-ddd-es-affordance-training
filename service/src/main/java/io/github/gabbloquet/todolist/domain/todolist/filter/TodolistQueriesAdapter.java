package io.github.gabbloquet.todolist.domain.todolist.filter;

import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class TodolistQueriesAdapter implements TodolistQueries {

    private final TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Override
    public Todolist filterBy(Filter filter) {
        List<Todolist.Task> filteredTasks;

        todolistUseCaseTransaction.start();
        Todolist todolist = todolistUseCaseTransaction.get();

        Predicate<Todolist.Task> filteringFunction =
                filter.equals(Filter.COMPLETED_TASKS)
                        ? getDone()
                        : getTodo();

        filteredTasks = todolist.render().stream()
                .filter(filteringFunction)
                .toList();

        return new Todolist(new ArrayList<>(filteredTasks));
    }

    private static Predicate<Todolist.Task> getDone() {
        return Todolist.Task::done;
    }

    private static Predicate<Todolist.Task> getTodo() {
        return task -> !task.done();
    }
}
