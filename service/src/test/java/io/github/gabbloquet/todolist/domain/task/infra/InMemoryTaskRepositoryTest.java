package io.github.gabbloquet.todolist.domain.task.infra;

import io.github.gabbloquet.todolist.task.infra.InMemoryTaskRepository;
import io.github.gabbloquet.todolist.task.model.Task;
import io.github.gabbloquet.todolist.task.model.TaskId;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskRepositoryTest {

    private final InMemoryTaskRepository inMemoryTaskRepository = new InMemoryTaskRepository();

    @Test
    void returns_an_empty_list_by_default() {
        assertEquals(List.of(), inMemoryTaskRepository.get());
    }

    @Test
    void saves_a_task() {
        // Given
        Task task = new Task(new TaskId());

        // When
        inMemoryTaskRepository.save(task);

        // Then
        List<Task> expectedTasks = List.of(
                task
        );
        assertEquals(expectedTasks, inMemoryTaskRepository.get());
    }

    @Test
    void gets_a_specific_task() {
        // Given
        Task taskOne = new Task(new TaskId());
        Task taskTwo = new Task(new TaskId());
        inMemoryTaskRepository.save(taskOne);
        inMemoryTaskRepository.save(taskTwo);


        // When
        Task returnedTask = inMemoryTaskRepository.get(taskTwo.taskId).get();

        // Then
        assertEquals(taskTwo, returnedTask);
    }

    @Test
    void returns_empty() {
        // When
        Optional<Task> returnedTask = inMemoryTaskRepository.get(new TaskId());

        // Then
        assertTrue(returnedTask.isEmpty());
    }
}
