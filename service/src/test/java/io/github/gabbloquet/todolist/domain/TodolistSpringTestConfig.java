package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.MockRegistry;
import io.github.gabbloquet.todolist.domain.features.*;
import io.github.gabbloquet.todolist.domain.models.Task;
import io.github.gabbloquet.todolist.domain.models.Todolist;
import io.github.gabbloquet.todolist.domain.models.TodolistCommandBus;
import io.github.gabbloquet.todolist.domain.models.TodolistEventBus;
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
    public Supplier<Task> taskSupplier(TaskUseCaseTransaction taskUseCaseTransaction) {
        return taskUseCaseTransaction;
    }

    @Bean
    @RequestScope
    public TodolistUseCaseTransaction todolistUseCaseTransaction(TodolistRepository todolistRepository) {
        return new TodolistUseCaseTransaction(todolistRepository);
    }

    @Bean
    @RequestScope
    public TaskUseCaseTransaction taskUseCaseTransaction(TaskRepository taskRepository) {
        return new TaskUseCaseTransaction(taskRepository);
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
            TodolistEventBus todolistEventBus
    ) {
        return new AddTaskUseCase(todolistEventBus);
    }

    @Bean
    public ModifyTaskUseCase updateTaskUseCase(
            Supplier<Task> taskSupplier,
            TodolistEventBus todolistEventBus
    ) {
        return new ModifyTaskUseCase(taskSupplier, todolistEventBus);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(
            Supplier<Task> taskSupplier,
            TodolistEventBus todolistEventBus
    ) {
        return new CompleteTaskUseCase(taskSupplier, todolistEventBus);
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
