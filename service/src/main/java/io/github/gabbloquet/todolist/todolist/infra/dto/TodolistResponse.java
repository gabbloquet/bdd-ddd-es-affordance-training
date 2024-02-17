package io.github.gabbloquet.todolist.todolist.infra.dto;

import io.github.gabbloquet.todolist.task.infra.dto.TaskDto;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public record TodolistResponse(List<EntityModel<TaskDto>> tasks) { }
