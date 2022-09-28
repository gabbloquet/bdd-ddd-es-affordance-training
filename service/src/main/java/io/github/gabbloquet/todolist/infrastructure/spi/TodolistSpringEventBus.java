package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.domain.model.TodolistEvent;
import io.github.gabbloquet.todolist.domain.model.TodolistEventBus;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.util.function.Supplier;

@Slf4j
public class TodolistSpringEventBus implements TodolistEventBus {

    @NonNull
    private final ApplicationEventPublisher eventPublisher;

    @NonNull
    private final Supplier<Todolist> todolistSupplier;

    public TodolistSpringEventBus(ApplicationEventPublisher eventPublisher,
                                  @NonNull Supplier<Todolist> todolistSupplier) {
        this.eventPublisher = eventPublisher;
        this.todolistSupplier = todolistSupplier;
    }

    @Override
    public void publish(TodolistEvent event) {
        log.info("[{}] todolist={} event={}",
                event.getClass().getSimpleName(),
                todolistSupplier.get(),
                event);

        eventPublisher.publishEvent(event);
    }
}
