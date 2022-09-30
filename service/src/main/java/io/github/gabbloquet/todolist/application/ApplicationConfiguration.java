package io.github.gabbloquet.todolist.application;

import io.github.gabbloquet.todolist.domain.TaskUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.features.*;
import io.github.gabbloquet.todolist.domain.models.Task;
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
