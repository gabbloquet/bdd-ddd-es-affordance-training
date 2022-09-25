package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class InMemoryTodolistRepositoryTest {

    private InMemoryTodolistRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTodolistRepository();
    }

    @Test
    void creates_an_empty_todolist_by_default() {
        List<Task> tasks = repository.get().get().render();
        Assertions.assertTrue(tasks.isEmpty());
    }

    @Test
    void saves_the_provided_todolist() {
        Task taskToAdd = new Task("Drink water");
        Todolist todolistToSave = new Todolist(Map.of(taskToAdd.id(), taskToAdd));

        repository.save(todolistToSave);

        Assertions.assertEquals(todolistToSave, repository.get().get());
    }
}
