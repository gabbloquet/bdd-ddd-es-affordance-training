package io.github.gabbloquet.todolist.domain.OutPort;

import io.github.gabbloquet.todolist.domain.Todolist;

public interface TodolistRepository {

    Todolist get();

    Todolist save(Todolist todolist);

}
