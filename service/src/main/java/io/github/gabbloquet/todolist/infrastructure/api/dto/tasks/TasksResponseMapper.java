package io.github.gabbloquet.todolist.infrastructure.api.dto.tasks;

import io.github.gabbloquet.todolist.infrastructure.api.TaskResource;
import io.github.gabbloquet.todolist.infrastructure.api.TodolistResource;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
@RequiredArgsConstructor
public class TasksResponseMapper {

    private final TodolistResource todolistResource = methodOn(TodolistResource.class);
    private final TaskResource taskResource = methodOn(TaskResource.class);

    public EntityModel<TaskDto> map(TaskDto task) {
        return EntityModel.of( //
                task,
                getSelfLink(task)
                    .andAffordance(afford(taskResource.modifyTask(null, task.id())))
                    .andAffordance(afford(taskResource.deleteTask(task.id()))).withRel("deleteOrModifyTask").withTitle("Modify or Delete a task"),
                getAddTaskAffordance(),
                getTodolistAffordance()
        );
    }

    private Link getSelfLink(TaskDto task) {
        return linkTo(taskResource.getTask(task.id())).withSelfRel();
    }

    private Link getAddTaskAffordance() {
        return linkTo(taskResource.addTask(null)).withRel("addTask").withTitle("Add a task");
    }

    private Link getTodolistAffordance() {
        return linkTo(todolistResource.get()).withRel("todolist").withTitle("Get todolist").withName("Get all tasks, todolist");
    }
}
