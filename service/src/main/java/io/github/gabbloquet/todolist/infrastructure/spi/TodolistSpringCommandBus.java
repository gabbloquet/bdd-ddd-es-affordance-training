package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.features.commands.TodolistCommand;
import io.github.gabbloquet.todolist.domain.models.TodolistCommandBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public class TodolistSpringCommandBus implements TodolistCommandBus {

    private final ApplicationEventPublisher eventPublisher;

    public TodolistSpringCommandBus(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void dispatch(TodolistCommand command) {
        log.info("[{}] command={}",
                command.getClass().getSimpleName(),
                command);
        eventPublisher.publishEvent(command);
    }
}
