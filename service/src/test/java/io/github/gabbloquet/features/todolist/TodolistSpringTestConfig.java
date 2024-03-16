package io.github.gabbloquet.features.todolist;

import io.cucumber.spring.ScenarioScope;
import io.github.gabbloquet.features.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.task.TaskRepository;
import io.github.gabbloquet.todolist.task.TaskService;
import io.github.gabbloquet.todolist.task.TaskUseCaseTransaction;
import io.github.gabbloquet.todolist.task.addTask.AddTaskUseCase;
import io.github.gabbloquet.todolist.task.completeTask.CompleteTaskUseCase;
import io.github.gabbloquet.todolist.task.deleteTask.DeleteTaskUseCase;
import io.github.gabbloquet.todolist.task.infra.TaskSpringEventBus;
import io.github.gabbloquet.todolist.task.model.Task;
import io.github.gabbloquet.todolist.task.model.TaskEventBus;
import io.github.gabbloquet.todolist.task.model.TaskId;
import io.github.gabbloquet.todolist.task.renameTask.RenameTaskUseCase;
import io.github.gabbloquet.todolist.todolist.TodolistEventHandler;
import io.github.gabbloquet.todolist.todolist.TodolistService;
import io.github.gabbloquet.todolist.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.todolist.deprioritizeTask.DeprioritizeTaskUseCase;
import io.github.gabbloquet.todolist.todolist.filter.TodolistQueries;
import io.github.gabbloquet.todolist.todolist.filter.TodolistQueriesAdapter;
import io.github.gabbloquet.todolist.todolist.infra.TodolistSpringCommandBus;
import io.github.gabbloquet.todolist.todolist.model.LocalDateTimeSupplier;
import io.github.gabbloquet.todolist.todolist.model.Todolist;
import io.github.gabbloquet.todolist.todolist.model.TodolistCommandBus;
import io.github.gabbloquet.todolist.todolist.openApplication.OpenApplicationUseCase;
import io.github.gabbloquet.todolist.todolist.prioritizeTask.PriorizeTaskUseCase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.annotation.RequestScope;

import java.util.function.Supplier;

import static org.mockito.Mockito.mock;

@TestConfiguration
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
            TodolistCommandBus todolistCommandBus,
            TodolistUseCaseTransaction todolistUseCaseTransaction
    ) {
        return new TodolistService(todolistCommandBus, todolistUseCaseTransaction);
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
            LocalDateTimeSupplier localDateTimeSupplier,
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
            LocalDateTimeSupplier localDateTimeSupplier
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
            LocalDateTimeSupplier localDateTimeSupplier
    ) {
        return new TodolistQueriesAdapter(todolistUseCaseTransaction, localDateTimeSupplier);
    }

    @Bean
    public Supplier<TaskId> taskIdProvider(){
        return TaskId::new;
    }

    @Bean
    public LocalDateTimeSupplier localDateTimeSupplier(){
        return mock(LocalDateTimeSupplier.class);
    }

}
