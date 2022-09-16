package io.github.gabbloquet.todolist.infrastructure.api.dto.todolist;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.LinkRelation;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TodolistRelations {

    public static final LinkRelation TASKS = LinkRelation.of("tasks");

}
