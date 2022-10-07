package io.github.gabbloquet.todolist.domain.todolist.filter;

import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.todolist.TodolistRepository;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistNotFound;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodolistQueriesAdapterTest {

    @Mock
    private TodolistRepository todolistRepository;

    @InjectMocks
    private TodolistQueriesAdapter todolistQueriesAdapter;

    Todolist.Task finishedTask = new Todolist.Task(new TaskId(), "a task", true);
    Todolist.Task finishedTaskTwo = new Todolist.Task(new TaskId(), "2 task", true);

    Todolist.Task todoTask = new Todolist.Task(new TaskId(), "another task", false);
    Todolist.Task todoTaskTwo = new Todolist.Task(new TaskId(), "another second task", false);

    @BeforeEach
    void setUp() {
        List<Todolist.Task> tasks = List.of(
                finishedTask,
                finishedTaskTwo,
                todoTask,
                todoTaskTwo
        );
        Todolist todolist = new Todolist(new ArrayList<>(tasks));

        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));
    }

    @Test
    void returns_completed_tasks() {
        Todolist todolist = todolistQueriesAdapter.filterBy(TodolistQueries.Filter.COMPLETED_TASKS);

        List<Todolist.Task> expectedTasks = List.of(
                finishedTask,
                finishedTaskTwo
        );
        Todolist expectedTodolist = new Todolist(new ArrayList<>(expectedTasks));

        Assertions.assertEquals(expectedTodolist, todolist);
    }

    @Test
    void returns_to_do_tasks() {
        Todolist todolist = todolistQueriesAdapter.filterBy(TodolistQueries.Filter.TO_DO_TASKS);

        List<Todolist.Task> expectedTasks = List.of(
                todoTask,
                todoTaskTwo
        );
        Todolist expectedTodolist = new Todolist(new ArrayList<>(expectedTasks));

        Assertions.assertEquals(expectedTodolist, todolist);
    }

    @Test
    void throws_todolist_not_found() {
        when(todolistRepository.get())
                .thenReturn(Optional.empty());

        TodolistNotFound exception = Assertions.assertThrows(TodolistNotFound.class, () ->
            todolistQueriesAdapter.filterBy(TodolistQueries.Filter.TO_DO_TASKS)
        );

        Assertions.assertEquals("Todolist not found.", exception.getMessage());
    }
}
