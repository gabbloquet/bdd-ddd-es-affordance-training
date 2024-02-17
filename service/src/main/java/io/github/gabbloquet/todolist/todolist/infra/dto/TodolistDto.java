package io.github.gabbloquet.todolist.todolist.infra.dto;

import io.github.gabbloquet.todolist.task.infra.dto.TaskDto;
import io.github.gabbloquet.todolist.todolist.model.Todolist;

import java.time.LocalDateTime;
import java.util.List;

public record TodolistDto(List<TaskDto> tasks) {
    public static TodolistDto from(Todolist todolist) {

        List<TaskDto> todolistTasks = todolist.render(LocalDateTime.now())
                .stream()
                .map(TaskDto::from)
                .toList();

        return new TodolistDto(todolistTasks);
    }
}
