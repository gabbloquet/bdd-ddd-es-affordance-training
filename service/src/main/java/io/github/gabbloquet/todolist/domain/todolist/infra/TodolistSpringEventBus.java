package io.github.gabbloquet.todolist.domain.todolist.infra;

import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistEvent;
import io.github.gabbloquet.todolist.domain.todolist.model.TodolistEventBus;
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

    @Override
    public void publish(TaskEvent event) {
        log.info("[{}] todolist={} event={}",
                event.getClass().getSimpleName(),
                todolistSupplier.get(),
                event);

        eventPublisher.publishEvent(event);
    }
}
