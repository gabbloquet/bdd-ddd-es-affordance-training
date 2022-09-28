package io.github.gabbloquet.todolist.domain.repositories;

import io.github.gabbloquet.todolist.application.annotations.DomainRepository;
import io.github.gabbloquet.todolist.domain.models.Todolist;

import java.util.Optional;

@DomainRepository
public interface TodolistRepository {

    Optional<Todolist> get();

    void save(Todolist todolist);
}
