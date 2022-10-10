package io.github.gabbloquet.todolist.domain.features.mappers;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TaskMapper {

    @ParameterType("((?:-?\\d+)|(?:\\d+))\\/((?:-?\\d+)|(?:\\d+))\\/((?:-?\\d+)|(?:\\d+)) à ((?:-?\\d+)|(?:\\d+)):((?:-?\\d+)|(?:\\d+))")
    public static LocalDateTime dateTime(String day, String month, String year, String hour, String minute) {
        return LocalDateTime.of(
                Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day),
                Integer.parseInt(hour),
                Integer.parseInt(minute)
        );
    }

    @DataTableType
    public static HashMap<String, LocalDateTime> tasks(DataTable dataTable) {
        HashMap tasks = new HashMap<String, LocalDateTime>();
        dataTable.asLists()
                .forEach(row -> tasks.put(row.get(0), LocalDateTime.parse(row.get(1))));
        return tasks;
    }
}
