package io.github.gabbloquet.todolist.domain.task.infra;

import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskEvent;
import io.github.gabbloquet.todolist.domain.task.model.TaskEventBus;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.util.function.Supplier;

@Slf4j
public class TaskSpringEventBus implements TaskEventBus {

    @NonNull
    private final ApplicationEventPublisher eventPublisher;

    @NonNull
    private final Supplier<Task> taskSupplier;

    public TaskSpringEventBus(ApplicationEventPublisher eventPublisher,
                              @NonNull Supplier<Task> taskSupplier) {
        this.eventPublisher = eventPublisher;
        this.taskSupplier = taskSupplier;
    }

    @Override
    public void publish(TaskEvent event) {
        log.info("[{}] todolist={} event={}",
                event.getClass().getSimpleName(),
                taskSupplier.get(),
                event);

        eventPublisher.publishEvent(event);
    }
}
