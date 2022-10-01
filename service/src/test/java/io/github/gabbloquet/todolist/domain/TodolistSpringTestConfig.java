package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.MockRegistry;
import io.github.gabbloquet.todolist.domain.task.TaskRepository;
import io.github.gabbloquet.todolist.domain.task.addTask.AddTaskUseCase;
import io.github.gabbloquet.todolist.domain.task.completeTask.CompleteTaskUseCase;
import io.github.gabbloquet.todolist.domain.task.model.TaskFactory;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.modifyTask.ModifyTaskUseCase;
import io.github.gabbloquet.todolist.domain.todolist.TodolistRepository;
import io.github.gabbloquet.todolist.domain.todolist.TodolistService;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask.DeprioritizeTaskUseCase;
import io.github.gabbloquet.todolist.domain.todolist.infra.TodolistSpringCommandBus;
import io.github.gabbloquet.todolist.domain.todolist.infra.TodolistSpringEventBus;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandBus;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistEventBus;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.PriorizeTaskUseCase;
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
            TodolistRepository todolistRepository,
            TodolistCommandBus todolistCommandBus
    ) {
        return new TodolistService(todolistRepository, todolistCommandBus);
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
            Supplier<TaskId> taskIdProvider,
            TodolistEventBus todolistEventBus
    ) {
        return new AddTaskUseCase(taskIdProvider, todolistEventBus);
    }

    @Bean
    public ModifyTaskUseCase updateTaskUseCase(
            TodolistEventBus todolistEventBus
    ) {
        return new ModifyTaskUseCase(todolistEventBus);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(
            TodolistEventBus todolistEventBus
    ) {
        return new CompleteTaskUseCase(todolistEventBus);
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

    @Bean
    public Supplier<TaskId> taskIdProvider(){
        return TaskId::new;
    }

    @Bean
    public TaskFactory taskFactory(){
        return new TaskFactory();
    }
}
