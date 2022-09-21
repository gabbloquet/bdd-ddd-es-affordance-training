package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class InMemoryTaskRepositoryTest {

    private InMemoryTaskRepository repository;

    @MockBean
    private TodolistRepository todolistRepository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository(todolistRepository);
    }

    @Test
    void get_task_from_todolist() {
        Task expectedTask = new Task(0, "Amazing task");
        Todolist todolist = initializeTodolistWith(expectedTask);
        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));

        Task receivedTask = repository.get(0).get();

        Assertions.assertEquals(receivedTask, expectedTask);
    }

    @Test
    void get_task_by_id() {
        Task expectedTask = new Task(1, "Amazing task");
        Task shouldNotBeTakeTask = new Task(0, "Im 0 but the second task");
        Todolist todolist = initializeTodolistWith(expectedTask, shouldNotBeTakeTask);
        when(todolistRepository.get())
                .thenReturn(Optional.of(todolist));

        Task receivedTask = repository.get(1).get();

        Assertions.assertEquals(receivedTask, expectedTask);
    }

    private Todolist initializeTodolistWith(Task... tasks) {
        return new Todolist(List.of(tasks));
    }
}