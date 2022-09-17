package io.github.gabbloquet.todolist.domain.deletetask;

import com.tngtech.jgiven.Stage;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class DeleteTaskStage extends Stage<DeleteTaskStage> {

    private Todolist todolist;

    public DeleteTaskStage a_todo_list_containing_$_and_$(String task1, String task2) {
        todolist = new Todolist();

        todolist.add(new Task(task1));
        todolist.add(new Task(task2));

        return self();
    }

    public DeleteTaskStage the_user_choose_to_clean_all_tasks() {
        todolist.clear();
        return self();
    }

    public void the_todo_list_contains_nothing() {
        Assertions
                .assertThat(todolist.tasks())
                .isEmpty();
    }

    public DeleteTaskStage the_user_choose_to_delete_$_task(String taskToDelete) {
        Task task = new Task(taskToDelete);

        todolist.delete(task);

        return self();
    }

    public void the_todo_list_contains_$(String task) {
        List<Task> expectedList = new ArrayList<>();
        expectedList.add(new Task(task));

        Assertions
            .assertThat(todolist.tasks())
            .isEqualTo(expectedList);
    }
}
