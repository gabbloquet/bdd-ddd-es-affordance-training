package io.github.gabbloquet.todolist.application;

import io.github.gabbloquet.todolist.domain.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.features.*;
import io.github.gabbloquet.todolist.domain.models.Todolist;
import io.github.gabbloquet.todolist.domain.models.TodolistCommandBus;
import io.github.gabbloquet.todolist.domain.models.TodolistEventBus;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import io.github.gabbloquet.todolist.infrastructure.spi.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import java.util.function.Supplier;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @RequestScope
    public Supplier<Todolist> todolistSupplier(TodolistUseCaseTransaction todolistUseCaseTransaction) {
        return todolistUseCaseTransaction;
    }

    @Bean
    @RequestScope
    public TodolistUseCaseTransaction todolistUseCaseTransaction(TodolistRepository todolistRepository) {
        return new TodolistUseCaseTransaction(todolistRepository);
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
    public TodolistCommandBus todolistCommandBus(
            ApplicationEventPublisher eventPublisher) {
        return new TodolistSpringCommandBus(eventPublisher);
    }

    @Bean
    public TodolistEventBus todolistEventBus(
            ApplicationEventPublisher eventPublisher,
            Supplier<Todolist> todolistSupplier) {
        return new TodolistSpringEventBus(eventPublisher, todolistSupplier);
    }

    @Bean
    public TodolistService todolistService(
            TodolistUseCaseTransaction todolistUseCaseTransaction,
            TodolistRepository todolistRepository,
            TodolistCommandBus todolistCommandBus
    ) {
        return new TodolistService(todolistUseCaseTransaction, todolistRepository, todolistCommandBus);
    }

    @Bean
    public AddTaskUseCase addTaskUseCase(
            TaskRepository taskRepository,
            Supplier<Todolist> todolistSupplier
    ) {
        return new AddTaskUseCase(taskRepository, todolistSupplier);
    }

    @Bean
    public ModifyTaskUseCase updateTaskUseCase(
            TaskRepository taskRepository,
            Supplier<Todolist> todolistSupplier,
            TodolistEventBus todolistEventBus
    ) {
        return new ModifyTaskUseCase(taskRepository, todolistSupplier, todolistEventBus);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(
            TaskRepository taskRepository,
            Supplier<Todolist> todolistSupplier
    ) {
        return new CompleteTaskUseCase(taskRepository, todolistSupplier);
    }

    @Bean
    public PriorizeTaskUseCase priorizeTaskUseCase(
            Supplier<Todolist> todolistSupplier
    ) {
        return new PriorizeTaskUseCase(todolistSupplier);
    }

    @Bean
    public DeprioritizeTaskUseCase deprioritizeTaskUseCase(
            Supplier<Todolist> todolistSupplier
    ) {
        return new DeprioritizeTaskUseCase(todolistSupplier);
    }
}
