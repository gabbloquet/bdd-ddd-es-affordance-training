package io.github.gabbloquet.todolist.domain.addtask;

import com.tngtech.jgiven.Stage;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.stream.Stream;

public class AddTaskStage extends Stage<AddTaskStage> {

    private Todolist todolist;

    public AddTaskStage an_empty_todo_list() {
        todolist = new Todolist();
        return self();
    }

    public AddTaskStage a_todo_list_containing_$(String task) {
        todolist = new Todolist();
        todolist.add(new Task(task));
        return self();
    }

    public AddTaskStage the_user_add_$_task(String task) {
        todolist.add(new Task(task));
        return self();
    }

    public void the_todo_list_contains_$(String... tasks) {
        List<Task> expectedTasks = Stream.of(tasks).map(Task::new).toList();
        Assertions
            .assertThat(todolist.tasks())
            .isEqualTo(expectedTasks);
    }
}
