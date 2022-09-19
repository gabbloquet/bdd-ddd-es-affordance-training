package io.github.gabbloquet.todolist.domain.checktodolist;

import com.tngtech.jgiven.Stage;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

public class CheckTodolistStage extends Stage<CheckTodolistStage> {

    private Todolist todolist;
    private List<String> tasks;

    public CheckTodolistStage an_empty_todo_list() {
        todolist = new Todolist();
        return self();
    }

    public CheckTodolistStage the_user_checks_his_todolist() {
        tasks = todolist.tasks().stream().map(Task::description).collect(Collectors.toList());
        return self();
    }

    public void no_task_is_returned() {
        Assertions.assertThat(tasks).isEqualTo(List.of());
    }

    public CheckTodolistStage a_todo_list_containing_$_and_$(String task1, String task2) {
        todolist = new Todolist();

        todolist.add(new Task(task1));
        todolist.add(new Task(2, task2));

        return self();
    }


    public void $_and_$_tasks_are_returned(String task1, String task2) {
        Assertions.assertThat(tasks).isEqualTo(List.of(task1, task2));
    }
}
