package io.github.gabbloquet.todolist.application;

import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.InPort.TodolistServiceImpl;
import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.infrastructure.spi.InMemoryTaskRepository;
import io.github.gabbloquet.todolist.infrastructure.spi.InMemoryTodolistRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public TodolistRepository getTodolistRepository() {
        return new InMemoryTodolistRepository();
    }

    @Bean
    public TaskRepository getTaskRepository(TodolistRepository todolistRepository) {
        return new InMemoryTaskRepository(todolistRepository);
    }

    @Bean
    TodolistService todolistService(TodolistRepository todolistRepository, TaskRepository taskRepository) {
        return new TodolistServiceImpl(todolistRepository, taskRepository);
    }
}
