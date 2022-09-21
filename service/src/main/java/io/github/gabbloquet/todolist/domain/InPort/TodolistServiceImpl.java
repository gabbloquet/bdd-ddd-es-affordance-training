package io.github.gabbloquet.todolist.domain.InPort;

import io.github.gabbloquet.todolist.domain.OutPort.TaskRepository;
import io.github.gabbloquet.todolist.domain.OutPort.TodolistRepository;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.spi.TaskNotFound;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class TodolistServiceImpl implements TodolistService {

    @NonNull
    private final TodolistRepository todolistRepository;

    @NonNull
    private final TaskRepository taskRepository;

    @Override
    public Todolist get() {
        return todolistRepository.get()
                .orElseThrow();
    }

    public Task getTask(int id) {
        return taskRepository.get(id)
                .orElseThrow(() -> new TaskNotFound(id));
    }

    public Task addTask(String task) {
        Optional<Todolist> todolist = todolistRepository.get();

        Task taskToAdd = new Task(task);
        todolist.ifPresent(foundTodolist -> {
            foundTodolist.add(taskToAdd);
            todolistRepository.save(foundTodolist);
        });

        return taskToAdd;
    }

    public void deleteTask(int id) {
        taskRepository.delete(id);
    }

    public Task modifyTask(int id, String update) {
        return taskRepository.modify(id, update)
                .orElseThrow(() -> new TaskNotFound(id));
    }

    public Todolist move(int id, int position) throws TaskNotFound {
        Optional<Todolist> todolist = todolistRepository.get();
        Optional<Task> taskToMove = taskRepository.get(id);

        if(todolist.isPresent() && taskToMove.isPresent()){
            todolist.get().move(taskToMove.get(), position);
            todolistRepository.save(todolist.get());
        } else {
//            TODO: ajouté le cas d'erreur, Not Found
        }

        return get();
    }
}
