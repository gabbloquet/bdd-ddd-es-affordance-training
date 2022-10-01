package io.github.gabbloquet.todolist.domain.todolist;

import io.github.gabbloquet.todolist.annotations.DomainRepository;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;

import java.util.Optional;

@DomainRepository
public interface TodolistRepository {

    Optional<Todolist> get();

    void save(Todolist todolist);
}
