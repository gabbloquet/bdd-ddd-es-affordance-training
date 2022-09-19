package io.github.gabbloquet.todolist.infrastructure.api.dto.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TasksResponseMapper {

    public EntityModel<TaskDto> map(TaskDto task) {
        return null;
    }
}
