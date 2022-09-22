package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.application.DomainService;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TodolistServiceImpl implements TodolistService {

    @NonNull
    private final TodolistRepository todolistRepository;

    @Override
    public Todolist addTask(Task task) {
        return todolistRepository.get()
            .map(todolist1 -> {
                todolist1.add(task);
                todolistRepository.save(todolist1);
                return todolist1;
            })
            .orElseThrow();
    }
}
