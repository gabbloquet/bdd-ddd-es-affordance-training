package io.github.gabbloquet.todolist.domain.reordertask;

import com.tngtech.jgiven.Stage;
import io.github.gabbloquet.todolist.domain.Todolist;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

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

    public ReorderTaskStage the_user_choose_to_put_$_on_$_position(String task, int position) {
        todolist.move(task, position);
        return self();
    }

    public void the_todo_list_contains_$_and_$(String task1, String task2) {
        List<String> expectedList = new ArrayList<>();
        expectedList.add(task1);
        expectedList.add(task2);

        Assertions
                .assertThat(todolist.tasks())
                .isEqualTo(expectedList);
    }

    public void the_todo_list_contains_$_$_$_and_$(String task1, String task2, String task3, String task4) {
        List<String> expectedList = new ArrayList<>();
        expectedList.add(task1);
        expectedList.add(task2);
        expectedList.add(task3);
        expectedList.add(task4);

        Assertions
                .assertThat(todolist.tasks())
                .isEqualTo(expectedList);
    }
}
