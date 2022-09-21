package io.github.gabbloquet.todolist.infrastructure.spi;

import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class InMemoryTaskRepository implements TaskRepository {

    @NonNull
    private TodolistRepository todolistRepository;

    @Override
    public Optional<Task> get(int id) {
        Optional<Todolist> todolist = todolistRepository.get();
        return todolist.map(foundTodolist ->
                foundTodolist.tasks().stream()
                    .filter(taskToCheck -> taskToCheck.id() == id)
                    .findAny()
                    .get()
        );
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<Task> modify(int id, String update) {
        return null;
    }
}
