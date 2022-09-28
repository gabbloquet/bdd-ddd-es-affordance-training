package io.github.gabbloquet.todolist.infrastructure.api.dto.tasks;

import io.github.gabbloquet.todolist.domain.models.Task;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ActionDto {
    public static List<ActionDto> from(Task task) {
        return null;
    }
}
