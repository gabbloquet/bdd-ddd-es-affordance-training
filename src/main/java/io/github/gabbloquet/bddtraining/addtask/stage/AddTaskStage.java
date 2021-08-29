package io.github.gabbloquet.bddtraining.addtask.stage;

import com.tngtech.jgiven.Stage;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class AddTaskStage extends Stage<AddTaskStage> {

    private List<String> todolist;

    public AddTaskStage an_empty_todo_list() {
        todolist = new ArrayList<>();
        return self();
    }

    public AddTaskStage the_user_add_a_$_task(String task) {
        todolist.add(task);
        return self();
    }

    public void the_todo_list_contains_$(String task) {
        Assertions
            .assertThat(todolist)
            .isEqualTo(List.of(task));
    }
}
