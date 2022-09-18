package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class InMemoryTodolistRepositoryTest {

    @Test
    void creates_an_empty_todolist() {
        InMemoryTodolistRepository repository = new InMemoryTodolistRepository();

        List<Task> tasks = repository.get().getTasks();
        Assertions.assertTrue(tasks.isEmpty());
    }

    @Test
    void save() {
        InMemoryTodolistRepository repository = new InMemoryTodolistRepository();
        Task taskToAdd = new Task("Drink water");
        Todolist todolistToSave = new Todolist(List.of(taskToAdd));

        repository.save(todolistToSave);

        Assertions.assertEquals(todolistToSave, repository.get());
    }
}