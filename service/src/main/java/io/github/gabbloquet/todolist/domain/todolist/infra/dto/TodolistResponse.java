package io.github.gabbloquet.todolist.domain.todolist.infra.dto;

import io.github.gabbloquet.todolist.domain.task.infra.dto.TaskDto;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public record TodolistResponse(List<EntityModel<TaskDto>> tasks) { }
