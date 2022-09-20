package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TodolistServiceImpl implements TodolistService {

    @NonNull
    private final TodolistRepository todolistRepository;

    @NonNull
    private final TaskRepository taskRepository;

    @Override
    public Todolist get() {
        return todolistRepository.get();
    }

    public Todolist move(int id, int position) {
        Todolist todolist = todolistRepository.get();
        Task taskToMove = taskRepository.get(id);

        todolist.move(taskToMove, position);

        return todolistRepository.save(todolist);
    }
}
