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

//    @Test
//    public void delete () {
//        deleteTaskStage
//            .given().()
//            .when().()
//            .then().();
//    }
}
