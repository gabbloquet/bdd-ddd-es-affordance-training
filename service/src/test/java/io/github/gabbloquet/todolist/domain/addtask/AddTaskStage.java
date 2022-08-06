package io.github.gabbloquet.todolist.domain.addtask;

import com.tngtech.jgiven.Stage;
import io.github.gabbloquet.todolist.domain.Task;
import io.github.gabbloquet.todolist.domain.Todolist;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> tasksInTodolist = todolist.tasks().stream().map(Task::task).collect(Collectors.toList());
        Assertions
            .assertThat(tasksInTodolist)
            .isEqualTo(List.of(tasks));
    }

    public AddTaskStage a_todo_list_containing_$(String task) {
        todolist = new Todolist(new ArrayList<>());
        todolist.add(task);
        return self();
    }
}
