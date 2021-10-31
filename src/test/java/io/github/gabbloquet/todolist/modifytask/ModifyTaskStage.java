package io.github.gabbloquet.todolist.modifytask;

import com.tngtech.jgiven.Stage;
import io.github.gabbloquet.todolist.domain.Todolist;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class ModifyTaskStage extends Stage<ModifyTaskStage> {

    private Todolist todolist;

    public ModifyTaskStage a_todo_list_containing_$_and_$(String task1, String task2) {
        todolist = new Todolist(new ArrayList<>());
        todolist.add(task1);
        todolist.add(task2);
        return self();
    }


    public ModifyTaskStage the_user_choose_to_modify_$_by_$(String taskToModify, String update) {
        todolist.modify(taskToModify, update);
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
}
