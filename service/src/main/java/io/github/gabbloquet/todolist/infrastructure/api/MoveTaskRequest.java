package io.github.gabbloquet.todolist.infrastructure.api;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class MoveTaskRequest {
    @NonNull
    private String task;

    @NonNull
    private int position;
}
