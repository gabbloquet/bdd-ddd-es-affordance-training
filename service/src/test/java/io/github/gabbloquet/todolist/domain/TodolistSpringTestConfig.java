package io.github.gabbloquet.todolist.domain;

import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.InPort.TodolistServiceImpl;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.annotation.RequestScope;

public class TodolistSpringTestConfig {

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
    @Primary
    public TodolistService todolistService(
            TodolistRepository todolistRepository
    ) {
        return new TodolistServiceImpl(todolistRepository);
    }
}
