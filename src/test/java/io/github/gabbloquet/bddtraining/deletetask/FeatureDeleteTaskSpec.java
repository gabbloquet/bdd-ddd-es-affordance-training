package io.github.gabbloquet.bddtraining.deletetask;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit5.JGivenExtension;
import io.github.gabbloquet.bddtraining.addtask.stage.AddTaskStage;
import io.github.gabbloquet.bddtraining.deletetask.stage.DeleteTaskStage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JGivenExtension.class)
public class FeatureDeleteTaskSpec {

    @ScenarioStage
    DeleteTaskStage deleteTaskStage;

    @Test
    public void delete_all_tasks () {
        deleteTaskStage
            .given().a_todo_list_containing_$_and_$("Clean the house", "Wash the car")
            .when().the_user_choose_to_clean_all_tasks()
            .then().the_todo_list_contains_nothing();
    }

    @Test
    public void delete_a_specific_task () {
        deleteTaskStage
                .given().a_todo_list_containing_$_and_$("Clean the house", "Wash the car")
                .when().the_user_choose_to_delete_$_task("Wash the car")
                .then().the_todo_list_contains_$("Clean the house");
    }
}
