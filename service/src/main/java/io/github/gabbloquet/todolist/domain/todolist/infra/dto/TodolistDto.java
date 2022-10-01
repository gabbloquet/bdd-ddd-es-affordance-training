package io.github.gabbloquet.todolist.domain.todolist.infra.dto;

import io.github.gabbloquet.todolist.domain.task.infra.dto.TaskDto;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;

import java.util.List;

public record TodolistDto(List<TaskDto> tasks) {
    public static TodolistDto from(Todolist todolist) {

        List<TaskDto> todolistTasks = todolist.render().stream()
                .map((TaskDto::from))
                .toList();

        return new TodolistDto(todolistTasks);
    }
}
