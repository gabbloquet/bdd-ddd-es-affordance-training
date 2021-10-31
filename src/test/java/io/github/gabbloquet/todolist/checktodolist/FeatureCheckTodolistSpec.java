package io.github.gabbloquet.todolist.checktodolist;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit5.JGivenExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JGivenExtension.class)
public class FeatureCheckTodolistSpec {

    @ScenarioStage
    CheckTodolistStage checkTodolistStage;

    @Test
    public void add_a_task_to_an_empty_todo_list() {
        checkTodolistStage
            .given().an_empty_todo_list()
            .when().the_user_checks_his_todolist()
            .then().no_task_is_returned();
    }

    @Test
    public void add_a_tasks_to_filled_todo_list() {
        checkTodolistStage
            .given().a_todo_list_containing_$_and_$("Clean the house", "Wash the car")
            .when().the_user_checks_his_todolist()
            .then().$_and_$_tasks_are_returned("Clean the house", "Wash the car");
    }
}
