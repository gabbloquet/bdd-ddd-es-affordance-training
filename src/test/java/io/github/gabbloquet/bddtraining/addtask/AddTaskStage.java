package io.github.gabbloquet.bddtraining.addtask;

import com.tngtech.jgiven.Stage;
import io.github.gabbloquet.bddtraining.domain.Todolist;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class AddTaskStage extends Stage<AddTaskStage> {

    private Todolist todolist;

    public AddTaskStage an_empty_todo_list() {
        todolist = new Todolist(new ArrayList<>());
        return self();
    }

    public AddTaskStage the_user_add_$_task(String task) {
        todolist.add(task);
        return self();
    }

    public void the_todo_list_contains_$(String... tasks) {
        Assertions
            .assertThat(todolist.tasks())
            .isEqualTo(List.of(tasks));
    }

    public AddTaskStage a_todo_list_containing_$(String... tasks) {
        todolist = new Todolist(new ArrayList<>());
        todolist.add(List.of(tasks));
        return self();
    }
}
