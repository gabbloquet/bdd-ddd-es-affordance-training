package io.github.gabbloquet.todolist.domain.OutPort;

import io.github.gabbloquet.todolist.application.annotations.DomainRepository;
import io.github.gabbloquet.todolist.domain.model.Todolist;

import java.util.Optional;

@DomainRepository
public interface TodolistRepository {

    Optional<Todolist> get();

    void save(Todolist todolist);
}
