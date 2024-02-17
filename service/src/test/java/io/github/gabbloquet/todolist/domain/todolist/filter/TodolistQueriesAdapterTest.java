package io.github.gabbloquet.todolist.domain.todolist.filter;

import io.github.gabbloquet.todolist.task.model.TaskId;
import io.github.gabbloquet.todolist.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.todolist.filter.TodolistQueries;
import io.github.gabbloquet.todolist.todolist.filter.TodolistQueriesAdapter;
import io.github.gabbloquet.todolist.todolist.model.LocalDateTimeSupplier;
import io.github.gabbloquet.todolist.todolist.model.Todolist;
import io.github.gabbloquet.todolist.todolist.model.TodolistNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodolistQueriesAdapterTest {

    @InjectMocks
    private TodolistQueriesAdapter todolistQueriesAdapter;

    @Mock
    private TodolistUseCaseTransaction todolistUseCaseTransaction;

    @Mock
    private LocalDateTimeSupplier localDateTimeSupplier;

    Todolist.Task finishedTask = new Todolist.Task(new TaskId(), "a task", LocalDateTime.now(), "2 jour(s)", true);
    Todolist.Task finishedTaskTwo = new Todolist.Task(new TaskId(), "2 task", LocalDateTime.now(), "1 jour(s)", true);

    Todolist.Task todoTask = new Todolist.Task(new TaskId(), "another task", LocalDateTime.now(), "22 heure(s)", false);
    Todolist.Task todoTaskTwo = new Todolist.Task(new TaskId(), "another second task", LocalDateTime.now(), "20 heure(s)", false);

    @BeforeEach
    void setUp() {
        List<Todolist.Task> tasks = List.of(
                finishedTask,
                finishedTaskTwo,
                todoTask,
                todoTaskTwo
        );
        Todolist todolist = new Todolist(new ArrayList<>(tasks));

        when(todolistUseCaseTransaction.get())
                .thenReturn(todolist);
    }

    @Test
    void returns_completed_tasks() {
        when(localDateTimeSupplier.get())
                .thenReturn(LocalDateTime.now());

        Todolist filteredTodolist = todolistQueriesAdapter.filterBy(TodolistQueries.Filter.COMPLETED_TASKS);

        List<Todolist.Task> expectedTasks = List.of(
                finishedTask,
                finishedTaskTwo
        );
        Todolist expectedTodolist = new Todolist(new ArrayList<>(expectedTasks));

        Assertions.assertEquals(expectedTodolist, filteredTodolist);
    }

    @Test
    void returns_to_do_tasks() {
        when(localDateTimeSupplier.get())
                .thenReturn(LocalDateTime.now());

        Todolist filteredTodolist = todolistQueriesAdapter.filterBy(TodolistQueries.Filter.TO_DO_TASKS);

        Assertions.assertEquals(todoTask.name(), filteredTodolist.render().get(0).name());
        Assertions.assertEquals(todoTaskTwo.name(), filteredTodolist.render().get(1).name());
    }

    @Test
    void throws_todolist_not_found() {
        when(todolistUseCaseTransaction.get())
                .thenReturn(null);

        TodolistNotFound exception = Assertions.assertThrows(TodolistNotFound.class, () ->
            todolistQueriesAdapter.filterBy(TodolistQueries.Filter.TO_DO_TASKS)
        );

        Assertions.assertEquals("Todolist not found.", exception.getMessage());
    }
}
