package io.github.gabbloquet.todolist.infrastructure.api.dto.todolist;

import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.TaskDto;

import java.util.List;

public record TodolistDto(List<TaskDto> tasks) {
    public static TodolistDto from(Todolist todolist) {

        List<TaskDto> todolistTasks = todolist.render().stream()
                .map((TaskDto::from))
                .toList();

        return new TodolistDto(todolistTasks);
    }
}
