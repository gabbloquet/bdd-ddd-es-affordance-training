package io.github.gabbloquet.todolist.task.infra.dto;

import io.github.gabbloquet.todolist.task.model.Task;
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
