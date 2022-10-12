package io.github.gabbloquet.todolist.domain.todolist.filter;

import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistNotFound;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class TodolistQueriesAdapter implements TodolistQueries {

    private final TodolistUseCaseTransaction todolistUseCaseTransaction;
    private final Supplier<LocalDateTime> localDateTimeSupplier;

    @Override
    public Todolist filterBy(Filter filter) {
        Todolist todolist = getTodolist();

        Predicate<Todolist.Task> filteringFunction =
                filter.equals(Filter.COMPLETED_TASKS)
                        ? getDone()
                        : getTodo();

        List<Todolist.Task> filteredTasks = todolist.render(localDateTimeSupplier.get()).stream()
                .filter(filteringFunction)
                .toList();

        return new Todolist(new ArrayList<>(filteredTasks));
    }

    private Todolist getTodolist() {
        todolistUseCaseTransaction.start();
        Todolist todolist = todolistUseCaseTransaction.get();

        if(todolist == null){
            throw new TodolistNotFound();
        }

        return todolist;
    }

    private static Predicate<Todolist.Task> getDone() {
        return Todolist.Task::done;
    }

    private static Predicate<Todolist.Task> getTodo() {
        return task -> !task.done();
    }
}
