package io.github.gabbloquet.todolist.domain;

import io.cucumber.spring.ScenarioScope;
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

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.function.Supplier;

import static org.mockito.Mockito.mock;

public class TodolistSpringTestConfig {

    @Bean
    @ScenarioScope
    public ScenarioState scenarioState() {
        return new ScenarioState();
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
            ApplicationEventPublisher eventPublisher,
            Supplier<Task> taskSupplier) {
        return new TaskSpringEventBus(eventPublisher, taskSupplier);
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
    public TaskRepository taskRepository() {
        return mock(TaskRepository.class);
    }

    @Bean
    public AddTaskUseCase addTaskUseCase(
            Supplier<TaskId> taskIdProvider,
            Supplier<LocalDateTime> localDateTimeSupplier,
            TaskEventBus taskEventBus
    ) {
        return new AddTaskUseCase(taskIdProvider, localDateTimeSupplier, taskEventBus);
    }

    @Bean
    public RenameTaskUseCase updateTaskUseCase(
            TaskEventBus taskEventBus
    ) {
        return new RenameTaskUseCase(taskEventBus);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(
            TaskEventBus taskEventBus,
            Supplier<LocalDateTime> localDateTimeSupplier
    ) {
        return new CompleteTaskUseCase(taskEventBus, localDateTimeSupplier);
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
            TodolistUseCaseTransaction todolistUseCaseTransaction,
            Supplier<LocalDateTime> localDateTimeSupplier
    ) {
        return new TodolistQueriesAdapter(todolistUseCaseTransaction, localDateTimeSupplier);
    }

    @Bean
    public Supplier<TaskId> taskIdProvider(){
        return TaskId::new;
    }

    @Bean
    public Supplier<LocalDateTime> localDateTimeSupplier(){
        return mock(Supplier.class);
    }

}
