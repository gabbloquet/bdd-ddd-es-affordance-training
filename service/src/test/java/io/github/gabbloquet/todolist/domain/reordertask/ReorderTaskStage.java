package io.github.gabbloquet.todolist.domain.reordertask;

import com.tngtech.jgiven.Stage;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class ReorderTaskStage extends Stage<ReorderTaskStage> {

    private Todolist todolist;

    public ReorderTaskStage a_todo_list_containing_$_and_$(String task1, String task2) {
        todolist = new Todolist();
        todolist.add(new Task(task1));
        todolist.add(new Task(task2));
        return self();
    }

    public ReorderTaskStage a_todo_list_containing_$_$_$_and_$(String task1, String task2, String task3, String task4) {
        todolist = new Todolist();
        todolist.add(new Task(task1));
        todolist.add(new Task(task2));
        todolist.add(new Task(task3));
        todolist.add(new Task(task4));
        return self();
    }

    public ReorderTaskStage the_user_choose_to_put_$_on_$_position(String choosedTask, int position) {
        todolist.move(new Task(choosedTask), position);
        return self();
    }

    public void the_todo_list_contains_$_and_$(String task1, String task2) {
        List<Task> expectedList = new ArrayList<>();
        expectedList.add(new Task(task1));
        expectedList.add(new Task(task2));

        Assertions
            .assertThat(todolist.getTasks())
            .isEqualTo(expectedList);
    }

    public void the_todo_list_contains_$_$_$_and_$(String task1, String task2, String task3, String task4) {
        List<Task> expectedList = new ArrayList<>();
        expectedList.add(new Task(task1));
        expectedList.add(new Task(task2));
        expectedList.add(new Task(task3));
        expectedList.add(new Task(task4));

        Assertions
                .assertThat(todolist.getTasks())
                .isEqualTo(expectedList);
    }
}
