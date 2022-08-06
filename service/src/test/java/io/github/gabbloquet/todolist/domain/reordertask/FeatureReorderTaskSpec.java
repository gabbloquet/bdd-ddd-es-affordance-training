package io.github.gabbloquet.todolist.domain.reordertask;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit5.JGivenExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JGivenExtension.class)
public class FeatureReorderTaskSpec {

    @ScenarioStage
    ReorderTaskStage reorderTaskStage;

    @Test
    public void place_a_task_on_first_position () {
        reorderTaskStage
            .given().a_todo_list_containing_$_and_$("Clean the house", "Buy cheese")
            .when().the_user_choose_to_put_$_on_$_position("Buy cheese", 1)
            .then().the_todo_list_contains_$_and_$("Buy cheese", "Clean the house");
    }

    @Test
    public void place_a_task_on_last_position () {
        reorderTaskStage
                .given().a_todo_list_containing_$_$_$_and_$("Clean the house", "Buy cheese", "Prepare coffee", "Wash the car")
                .when().the_user_choose_to_put_$_on_$_position("Clean the house", 4)
                .then().the_todo_list_contains_$_$_$_and_$("Buy cheese", "Prepare coffee", "Wash the car", "Clean the house");
    }

    @Test
    public void place_a_task_on_second_position () {
        reorderTaskStage
                .given().a_todo_list_containing_$_$_$_and_$("Clean the house", "Buy cheese", "Prepare coffee", "Wash the car")
                .when().the_user_choose_to_put_$_on_$_position("Wash the car", 2)
                .then().the_todo_list_contains_$_$_$_and_$("Clean the house",  "Wash the car", "Buy cheese", "Prepare coffee");
    }
}
