package io.github.gabbloquet.todolist.domain.todolist.model;

import java.time.LocalDateTime;

public class LocalDateTimeSupplier {
    public LocalDateTime get() {
        return LocalDateTime.now();
    }
}
