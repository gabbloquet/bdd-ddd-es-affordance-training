package io.github.gabbloquet.todolist.domain.OutPort;

import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;

public interface TodolistRepository {

    Todolist get();

    Todolist save(Todolist todolist);
}
