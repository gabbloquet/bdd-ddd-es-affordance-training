package io.github.gabbloquet.todolist.infrastructure.api.dto.todolist;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.LinkRelation;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TodolistRelations {

    public static final LinkRelation TODOLIST = LinkRelation.of("todolist");
    public static final LinkRelation ADD_TASK = LinkRelation.of("addTask");

}
