package io.github.gabbloquet.bddtraining.deletetask.stage;

import com.tngtech.jgiven.Stage;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Java6BDDSoftAssertionsProvider;

import java.util.ArrayList;
import java.util.List;

public class DeleteTaskStage extends Stage<DeleteTaskStage> {

    private List<String> todolist;

    public DeleteTaskStage a_todo_list_containing_$_and_$(String task1, String task2) {
        todolist = new ArrayList<>();
        todolist.add(task1);
        todolist.add(task2);
        return self();
    }

    public DeleteTaskStage the_user_choose_to_clean_all_tasks() {
        todolist.clear();
        return self();
    }

    public void the_todo_list_contains_nothing() {
        Assertions
                .assertThat(todolist)
                .isEmpty();
    }

    public DeleteTaskStage the_user_choose_to_delete_$_task(String task) {
        todolist.remove(task);
        return self();
    }

    public void the_todo_list_contains_$(String task) {
        List<String> expectedList = new ArrayList<>();
        expectedList.add(task);

        Assertions
                .assertThat(todolist)
                .isEqualTo(expectedList);
    }
}
