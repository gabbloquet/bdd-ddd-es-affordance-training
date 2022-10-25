package io.github.gabbloquet.todolist.domain.task.infra;

import io.github.gabbloquet.todolist.domain.task.TaskCommand;
import io.github.gabbloquet.todolist.domain.task.TaskService;
import io.github.gabbloquet.todolist.domain.task.completeTask.CompleteTask;
import io.github.gabbloquet.todolist.domain.task.deleteTask.DeleteTask;
import io.github.gabbloquet.todolist.domain.task.infra.dto.TaskDto;
import io.github.gabbloquet.todolist.domain.task.infra.dto.TaskRequest;
import io.github.gabbloquet.todolist.domain.task.infra.dto.TasksResponseAssembler;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;
import io.github.gabbloquet.todolist.domain.task.model.TaskState;
import io.github.gabbloquet.todolist.domain.task.renameTask.RenameTask;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        return tasksResponseAssembler.map(TaskDto.from(task), List.of());
    }

    @PutMapping("/{id}/rename")
    public EntityModel<TaskDto> rename(@RequestBody TaskRequest taskRequest, @PathVariable UUID id) {

        TaskCommand command = RenameTask.builder()
                .taskId(TaskId.from(id))
                .update(taskRequest.description())
                .build();

        TaskState modifiedTask = taskService.execute(command);

        return tasksResponseAssembler.map(TaskDto.from(modifiedTask), List.of());
    }

    @PutMapping("/{id}/complete")
    public EntityModel<TaskDto> complete(@PathVariable UUID id) {

        TaskCommand command = CompleteTask.builder()
                .taskId(TaskId.from(id))
                .build();

        TaskState completedTask = taskService.execute(command);

        return tasksResponseAssembler.map(TaskDto.from(completedTask), List.of());
    }

    @DeleteMapping("/{id}")
    public RepresentationModel<?> deleteTask(@PathVariable UUID id) {
        TaskCommand command = DeleteTask.builder()
                .taskId(TaskId.from(id))
                .build();

        taskService.execute(command);

        return tasksResponseAssembler.get();
    }
}
