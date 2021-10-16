package io.github.gabbloquet.bddtraining.modifytask;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit5.JGivenExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JGivenExtension.class)
public class FeatureModifyTaskSpec {

    @ScenarioStage
    ModifyTaskStage modifyTaskStage;

    @Test
    public void modify_a_task () {
        modifyTaskStage
            .given().a_todo_list_containing_$_and_$("Clean the house", "Wash the car")
            .when().the_user_choose_to_modify_$_by_$("Clean the house", "Buy cheese")
            .then().the_todo_list_contains_$_and_$("Buy cheese", "Wash the car");
    }
}
