package io.github.gabbloquet.todolist.domain;

import io.cucumber.spring.ScenarioScope;
import io.github.gabbloquet.todolist.MockRegistry;
import io.github.gabbloquet.todolist.domain.task.TaskRepository;
import io.github.gabbloquet.todolist.domain.task.TaskService;
import io.github.gabbloquet.todolist.domain.task.TaskUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.task.addTask.AddTaskUseCase;
import io.github.gabbloquet.todolist.domain.task.completeTask.CompleteTaskUseCase;
import io.github.gabbloquet.todolist.domain.task.deleteTask.DeleteTaskUseCase;
import io.github.gabbloquet.todolist.domain.task.infra.TaskSpringEventBus;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskEventBus;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.renameTask.RenameTaskUseCase;
import io.github.gabbloquet.todolist.domain.todolist.TodolistEventHandler;
import io.github.gabbloquet.todolist.domain.todolist.TodolistService;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask.DeprioritizeTaskUseCase;
import io.github.gabbloquet.todolist.domain.todolist.filter.TodolistQueries;
import io.github.gabbloquet.todolist.domain.todolist.filter.TodolistQueriesAdapter;
import io.github.gabbloquet.todolist.domain.todolist.infra.TodolistSpringCommandBus;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandBus;
import io.github.gabbloquet.todolist.domain.todolist.openApplication.OpenApplicationUseCase;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.PriorizeTaskUseCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.annotation.RequestScope;

import java.util.function.Supplier;

public class TodolistSpringTestConfig {

    @Bean
    @ScenarioScope
    public ScenarioState scenarioState() {
        return new ScenarioState();
    }

    @Bean
    public MockRegistry mockRegistry() {
        return new MockRegistry();
    }

    @Bean
    @RequestScope
    public Supplier<Todolist> todolistSupplier(TodolistUseCaseTransaction todolistUseCaseTransaction) {
        return todolistUseCaseTransaction;
    }

    @Bean
    @RequestScope
    public TodolistUseCaseTransaction todolistUseCaseTransaction(TaskRepository taskRepository) {
        return new TodolistUseCaseTransaction(taskRepository);
    }

    @Bean
    @RequestScope
    public Supplier<Task> taskSupplier(TaskUseCaseTransaction taskUseCaseTransaction) {
        return taskUseCaseTransaction;
    }

    @Bean
    @RequestScope
    public TaskUseCaseTransaction taskUseCaseTransaction(TaskRepository taskRepository) {
        return new TaskUseCaseTransaction(taskRepository);
    }

    @Bean
    @Primary
    public TaskEventBus todolistEventBus(
            MockRegistry mockRegistry,
            ApplicationEventPublisher eventPublisher,
            Supplier<Task> taskSupplier) {
        return mockRegistry.spy(new TaskSpringEventBus(eventPublisher, taskSupplier));
    }

    @Bean
    public TodolistCommandBus todolistCommandBus(
            ApplicationEventPublisher eventPublisher) {
        return new TodolistSpringCommandBus(eventPublisher);
    }

    @Bean
    public TodolistService todolistService(
            TodolistCommandBus todolistCommandBus
    ) {
        return new TodolistService(todolistCommandBus);
    }

    @Bean
    public TaskService taskService(
            TaskUseCaseTransaction taskUseCaseTransaction,
            TodolistCommandBus todolistCommandBus
    ) {
        return new TaskService(taskUseCaseTransaction, todolistCommandBus);
    }

    @Bean
    public TodolistEventHandler todolistEventHandler(
            TodolistUseCaseTransaction todolistUseCaseTransaction) {
        return new TodolistEventHandler(todolistUseCaseTransaction);
    }

    @Bean
    @RequestScope
    public TaskRepository taskRepository(MockRegistry registry) {
        return registry.mock(TaskRepository.class);
    }

    @Bean
    public AddTaskUseCase addTaskUseCase(
            Supplier<TaskId> taskIdProvider,
            TaskEventBus taskEventBus
    ) {
        return new AddTaskUseCase(taskIdProvider, taskEventBus);
    }

    @Bean
    public RenameTaskUseCase updateTaskUseCase(
            TaskEventBus taskEventBus
    ) {
        return new RenameTaskUseCase(taskEventBus);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(
            TaskEventBus taskEventBus
    ) {
        return new CompleteTaskUseCase(taskEventBus);
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase(
            TaskEventBus taskEventBus
    ) {
        return new DeleteTaskUseCase(taskEventBus);
    }

    @Bean
    public PriorizeTaskUseCase priorizeTaskUseCase(
            TodolistUseCaseTransaction todolistUseCaseTransaction
    ) {
        return new PriorizeTaskUseCase(todolistUseCaseTransaction);
    }

    @Bean
    public DeprioritizeTaskUseCase deprioritizeTaskUseCase(
            TodolistUseCaseTransaction todolistUseCaseTransaction
    ) {
        return new DeprioritizeTaskUseCase(todolistUseCaseTransaction);
    }

    @Bean
    public OpenApplicationUseCase openApplicationUseCase(
            TodolistUseCaseTransaction todolistUseCaseTransaction
    ) {
        return new OpenApplicationUseCase(todolistUseCaseTransaction);
    }

    @Bean
    public TodolistQueries todolistQueries(
            Supplier<Todolist> todolistSupplier
    ) {
        return new TodolistQueriesAdapter(todolistSupplier);
    }

    @Bean
    public Supplier<TaskId> taskIdProvider(){
        return TaskId::new;
    }
}
