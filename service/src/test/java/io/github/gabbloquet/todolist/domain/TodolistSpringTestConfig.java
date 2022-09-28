package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.MockRegistry;
import io.github.gabbloquet.todolist.domain.features.*;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.model.TodolistCommandBus;
import io.github.gabbloquet.todolist.domain.model.TodolistEventBus;
import io.github.gabbloquet.todolist.domain.repositories.TaskRepository;
import io.github.gabbloquet.todolist.domain.repositories.TodolistRepository;
import io.github.gabbloquet.todolist.infrastructure.spi.TodolistSpringCommandBus;
import io.github.gabbloquet.todolist.infrastructure.spi.TodolistSpringEventBus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.annotation.RequestScope;

import java.util.function.Supplier;

public class TodolistSpringTestConfig {

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
    public MockRegistry mockRegistry() {
        return new MockRegistry();
    }

    @Bean
    @Primary
    public TodolistEventBus todolistEventBus(
            MockRegistry mockRegistry,
            ApplicationEventPublisher eventPublisher,
            Supplier<Todolist> todolistSupplier) {
        return mockRegistry.spy(new TodolistSpringEventBus(eventPublisher, todolistSupplier));
    }

    @Bean
    public TodolistCommandBus todolistCommandBus(
            ApplicationEventPublisher eventPublisher) {
        return new TodolistSpringCommandBus(eventPublisher);
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
    @RequestScope
    public TodolistRepository todolistRepository(MockRegistry registry) {
        return registry.mock(TodolistRepository.class);
    }

    @Bean
    @RequestScope
    public TaskRepository taskRepository(MockRegistry registry) {
        return registry.mock(TaskRepository.class);
    }

    @Bean
    public AddTaskUseCase addTaskUseCase(
            TaskRepository taskRepository,
            Supplier<Todolist> todolistSupplier
    ) {
        return new AddTaskUseCase(taskRepository, todolistSupplier);
    }

    @Bean
    public UpdateTaskUseCase updateTaskUseCase(
            TaskRepository taskRepository,
            Supplier<Todolist> todolistSupplier,
            TodolistEventBus todolistEventBus
    ) {
        return new UpdateTaskUseCase(taskRepository, todolistSupplier, todolistEventBus);
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
