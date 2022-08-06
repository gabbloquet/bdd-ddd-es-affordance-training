package io.github.gabbloquet.todolist.domain.reordertask;

import com.tngtech.jgiven.Stage;
import io.github.gabbloquet.todolist.domain.Task;
import io.github.gabbloquet.todolist.domain.Todolist;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReorderTaskStage extends Stage<ReorderTaskStage> {

    private Todolist todolist;

    public ReorderTaskStage a_todo_list_containing_$_and_$(String task1, String task2) {
        todolist = new Todolist(new ArrayList<>());
        todolist.add(task1);
        todolist.add(task2);
        return self();
    }

    public ReorderTaskStage a_todo_list_containing_$_$_$_and_$(String task1, String task2, String task3, String task4) {
        todolist = new Todolist(new ArrayList<>());
        todolist.add(task1);
        todolist.add(task2);
        todolist.add(task3);
        todolist.add(task4);
        return self();
    }

    public ReorderTaskStage the_user_choose_to_put_$_on_$_position(String choosedTask, int position) {
        Map<String, Task> map = todolist.tasks().stream().collect(Collectors.toMap(Task::task, Function.identity()));
        todolist.move(map.get(choosedTask).id(), position);
        return self();
    }

    public void the_todo_list_contains_$_and_$(String task1, String task2) {
        List<String> expectedList = new ArrayList<>();
        expectedList.add(task1);
        expectedList.add(task2);

        List<String> tasksInTodolist = todolist.tasks().stream().map(Task::task).collect(Collectors.toList());

        Assertions
            .assertThat(tasksInTodolist)
            .isEqualTo(expectedList);
    }

    public void the_todo_list_contains_$_$_$_and_$(String task1, String task2, String task3, String task4) {
        List<String> expectedList = new ArrayList<>();
        expectedList.add(task1);
        expectedList.add(task2);
        expectedList.add(task3);
        expectedList.add(task4);

        List<String> tasksInTodolist = todolist.tasks().stream().map(Task::task).collect(Collectors.toList());

        Assertions
                .assertThat(tasksInTodolist)
                .isEqualTo(expectedList);
    }
}
