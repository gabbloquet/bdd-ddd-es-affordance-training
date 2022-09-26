package io.github.gabbloquet.todolist.application;

import io.github.gabbloquet.todolist.domain.features.*;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
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
    public TodolistService openApplicationUseCase(
            TodolistRepository todolistRepository,
            Todolist todolist
    ) {
        return new TodolistService(todolistRepository, todolist);
    }

    @Bean
    public AddTaskUseCase addTaskUseCase(
            TaskRepository taskRepository,
            TodolistRepository todolistRepository,
            Todolist todolist
    ) {
        return new AddTaskUseCase(taskRepository, todolistRepository, todolist);
    }

    @Bean
    public UpdateTaskUseCase updateTaskUseCase(
            TaskRepository taskRepository,
            TodolistRepository todolistRepository,
            Todolist todolist
    ) {
        return new UpdateTaskUseCase(taskRepository, todolistRepository, todolist);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(
            TaskRepository taskRepository,
            TodolistRepository todolistRepository,
            Todolist todolist
    ) {
        return new CompleteTaskUseCase(taskRepository, todolistRepository, todolist);
    }

    @Bean
    public PriorizeTaskUseCase priorizeTaskUseCase(
            Todolist todolist,
            TodolistRepository todolistRepository
    ) {
        return new PriorizeTaskUseCase(todolist, todolistRepository);
    }

    @Bean
    public DeprioritizeTaskUseCase deprioritizeTaskUseCase(
            Todolist todolist,
            TodolistRepository todolistRepository
    ) {
        return new DeprioritizeTaskUseCase(todolist, todolistRepository);
    }
}
