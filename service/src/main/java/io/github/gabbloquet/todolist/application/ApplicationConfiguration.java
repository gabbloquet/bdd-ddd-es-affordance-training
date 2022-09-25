package io.github.gabbloquet.todolist.application;

import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.InPort.TodolistServiceImpl;
import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.spi.InMemoryTaskRepository;
import io.github.gabbloquet.todolist.infrastructure.spi.InMemoryTodolistRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Todolist todolist() {
        return new Todolist();
    }

    @Bean
    public TodolistRepository todolistRepository() {
        return new InMemoryTodolistRepository();
    }

    @Bean
    public TaskRepository taskRepository() {
        return new InMemoryTaskRepository();
    }

    @Bean
    TodolistService todolistService(TaskRepository taskRepository, TodolistRepository todolistRepository, Todolist todolist) {
        return new TodolistServiceImpl(taskRepository, todolistRepository, todolist);
    }
}
