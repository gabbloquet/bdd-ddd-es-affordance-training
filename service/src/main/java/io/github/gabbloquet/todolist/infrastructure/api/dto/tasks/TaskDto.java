package io.github.gabbloquet.todolist.infrastructure.api.dto.tasks;

import io.github.gabbloquet.todolist.domain.model.Task;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDto {
    public static TaskDto from(Task task) {
        return null;
    }
}
