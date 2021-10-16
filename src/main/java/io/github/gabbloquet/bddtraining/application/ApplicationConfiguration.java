package io.github.gabbloquet.bddtraining.application;

import io.github.gabbloquet.bddtraining.domain.InPort.TodolistService;
import io.github.gabbloquet.bddtraining.domain.InPort.TodolistServiceImpl;
import io.github.gabbloquet.bddtraining.infrastructure.spi.InMemoryTodolistRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private InMemoryTodolistRepository getTodolistRepository() {
        return new InMemoryTodolistRepository();
    }

    @Bean
    TodolistService todolistService() {
        return new TodolistServiceImpl(getTodolistRepository());
    }
}
