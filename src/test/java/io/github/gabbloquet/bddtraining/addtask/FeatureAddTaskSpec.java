package io.github.gabbloquet.bddtraining.addtask;

import io.github.gabbloquet.bddtraining.addtask.stage.AddTaskStage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit5.JGivenExtension;

@ExtendWith(JGivenExtension.class)
public class FeatureAddTaskSpec {

    @ScenarioStage
    AddTaskStage addTaskStage;

    @Test
    public void add_a_task_to_empty_todo_list() {
        addTaskStage
            .given().an_empty_todo_list()
            .when().the_user_add_a_$_task("Buy a cheese")
            .then().the_todo_list_contains_$("Buy a cheese");
    }
}
