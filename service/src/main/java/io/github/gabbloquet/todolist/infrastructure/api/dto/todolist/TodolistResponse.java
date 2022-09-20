package io.github.gabbloquet.todolist.infrastructure.api.dto.todolist;

import io.github.gabbloquet.todolist.infrastructure.api.dto.tasks.TaskDto;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public record TodolistResponse(List<EntityModel<TaskDto>> tasks) { }
