package io.github.gabbloquet.todolist.domain.todolist.filter;

import io.github.gabbloquet.todolist.domain.todolist.TodolistRepository;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistNotFound;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class TodolistQueriesAdapter implements TodolistQueries {

    private final TodolistRepository todolistRepository;

    @Override
    public Todolist filterBy(Filter filter) {
        List<Todolist.Task> filteredTasks;
        Todolist todolist = todolistRepository.get()
                .orElseThrow(TodolistNotFound::new);

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
