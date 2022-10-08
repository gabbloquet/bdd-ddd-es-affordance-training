package io.github.gabbloquet.todolist.domain.features.mappers;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.github.gabbloquet.todolist.domain.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.domain.task.model.Task;
import io.github.gabbloquet.todolist.domain.task.model.TaskId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskMapper {

//    @ParameterType("((?:-?\\d+)|(?:\\d+))\\/((?:-?\\d+)|(?:\\d+))\\/((?:-?\\d+)|(?:\\d+)) à ((?:-?\\d+)|(?:\\d+)):((?:-?\\d+)|(?:\\d+))")
//    public static LocalDateTime dateTime(int day, int month, int year, int hour, int minute) {
//        return LocalDateTime.of(year, month, day, hour, minute);
//    }
//
//    @DataTableType
//    public static Set<Task> tasks(DataTable dataTable) {
//        return dataTable.asLists().stream()
//                .map(row -> {
//                            TaskId taskId = new TaskId();
//                            TaskCreated event = TaskCreated.builder()
//                                    .taskId(taskId)
//                                    .description(row.get(0))
//                                    .creationTime(LocalDateTime.parse(row.get(1)))
//                                    .isCompleted(true)
//                                    .build();
//                            return new Task(taskId, new ArrayList<>(List.of(event)));
//                        }
//                ).collect(Collectors.toUnmodifiableSet());
//    }
}
