package io.github.gabbloquet.todolist.domain.task.infra;

import io.github.gabbloquet.todolist.domain.task.TaskCommand;
import io.github.gabbloquet.todolist.domain.task.TaskService;
import io.github.gabbloquet.todolist.domain.task.addTask.AddTask;
import io.github.gabbloquet.todolist.domain.task.addTask.OpenTask;
import io.github.gabbloquet.todolist.domain.task.infra.dto.TaskDto;
import io.github.gabbloquet.todolist.domain.task.infra.dto.TaskRequest;
import io.github.gabbloquet.todolist.domain.task.infra.dto.TasksResponseAssembler;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskState;
import io.github.gabbloquet.todolist.domain.task.modifyTask.ModifyTask;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskResource {

    @NonNull
    private final TasksResponseAssembler tasksResponseAssembler;

    @NonNull
    private final TaskService taskService;


    @GetMapping("/{id}")
    public EntityModel<TaskDto> getTask(@PathVariable UUID id) {

        TaskState task = taskService.getTask(id);

        return tasksResponseAssembler.map(TaskDto.from(task));
    }

    @PostMapping()
    public EntityModel<TaskDto> addTask(@RequestBody TaskRequest taskRequest) {

        OpenTask command = AddTask.builder()
                .description(taskRequest.description())
                .build();

        TaskState createdTask = taskService.execute(command);

        return tasksResponseAssembler.map(TaskDto.from(createdTask));
    }

    @PutMapping("/{id}")
    public EntityModel<TaskDto> modifyTask(@RequestBody TaskRequest taskRequest, @PathVariable UUID id) {

        TaskCommand command = ModifyTask.builder()
                .taskId(TaskId.from(id))
                .update(taskRequest.description())
                .build();

        TaskState modifiedTask = taskService.execute(command);

        return tasksResponseAssembler.map(TaskDto.from(modifiedTask));
    }

    @DeleteMapping("/{id}")
    public RepresentationModel<?> deleteTask(@PathVariable UUID id) {
//        TaskCommand command = new DeleteTask.builder().build();

//        taskService.execute();

        return tasksResponseAssembler.get();
    }
}
