package io.github.gabbloquet.todolist.domain.todolist.infra;

import io.github.gabbloquet.todolist.domain.task.TaskRepository;
import io.github.gabbloquet.todolist.domain.task.TaskService;
import io.github.gabbloquet.todolist.domain.task.TaskUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.task.addTask.AddTaskUseCase;
import io.github.gabbloquet.todolist.domain.task.completeTask.CompleteTaskUseCase;
import io.github.gabbloquet.todolist.domain.task.deleteTask.DeleteTaskUseCase;
import io.github.gabbloquet.todolist.domain.task.infra.InMemoryTaskRepository;
import io.github.gabbloquet.todolist.domain.task.infra.TaskSpringEventBus;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskEventBus;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.modifyTask.ModifyTaskUseCase;
import io.github.gabbloquet.todolist.domain.todolist.TodolistEventHandler;
import io.github.gabbloquet.todolist.domain.todolist.TodolistRepository;
import io.github.gabbloquet.todolist.domain.todolist.TodolistService;
import io.github.gabbloquet.todolist.domain.todolist.TodolistUseCaseTransaction;
import io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask.DeprioritizeTaskUseCase;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistCommandBus;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.PriorizeTaskUseCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import java.util.function.Supplier;

@Configuration
public class TodolistSpringConfig {

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
    public TaskEventBus todolistEventBus(
            ApplicationEventPublisher eventPublisher,
            Supplier<Task> taskSupplier) {
        return new TaskSpringEventBus(eventPublisher, taskSupplier);
    }

    @Bean
    public TodolistService todolistService(
            TodolistRepository todolistRepository,
            TodolistCommandBus todolistCommandBus
    ) {
        return new TodolistService(todolistRepository, todolistCommandBus);
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
    public AddTaskUseCase addTaskUseCase(
            Supplier<TaskId> taskIdProvider,
            TaskEventBus taskEventBus
    ) {
        return new AddTaskUseCase(taskIdProvider, taskEventBus);
    }

    @Bean
    public ModifyTaskUseCase updateTaskUseCase(
            TaskEventBus taskEventBus
    ) {
        return new ModifyTaskUseCase(taskEventBus);
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
    public Supplier<TaskId> taskIdProvider(){
        return TaskId::new;
    }

}
