package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TodolistServiceImpl implements TodolistService {

    @NonNull
    private final TodolistRepository todolistRepository;

    @Override
    public Todolist get() {
        return todolistRepository.get();
    }

    public Todolist move(Task task, int position) {
        var todolist = todolistRepository.get();

        todolist.move(task, position);

        return todolistRepository.save(todolist);
    }
}
