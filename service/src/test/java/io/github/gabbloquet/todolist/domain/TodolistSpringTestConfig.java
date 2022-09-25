package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.MockRegistry;
import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.InPort.TodolistServiceImpl;
import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.annotation.RequestScope;

public class TodolistSpringTestConfig {

    @Bean
    public Todolist todolist() {
        return new Todolist();
    }

    @Bean
    public MockRegistry mockRegistry() {
        return new MockRegistry();
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
    @Primary
    public TodolistService todolistService(
            TaskRepository taskRepository,
            TodolistRepository todolistRepository,
            Todolist todolist
    ) {
        return new TodolistServiceImpl(taskRepository, todolistRepository, todolist);
    }
}
