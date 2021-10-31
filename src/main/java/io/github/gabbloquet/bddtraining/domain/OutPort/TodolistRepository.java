package io.github.gabbloquet.bddtraining.domain.OutPort;

import io.github.gabbloquet.bddtraining.domain.Todolist;

public interface TodolistRepository {

    Todolist get();

    Todolist save(Todolist todolist);

}
