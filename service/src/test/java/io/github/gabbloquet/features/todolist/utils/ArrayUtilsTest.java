package io.github.gabbloquet.features.todolist.utils;

import io.github.gabbloquet.todolist.utils.ArrayUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtilsTest {

    @Test
    void shouldReturnAnEmptyListIfEmpty() {
        List<String> emptyList = new ArrayList<String>();

        ArrayUtils.moveToPosition(emptyList, "shouldBeOmitted", 1);

        Assertions
                .assertThat(emptyList)
                .isEqualTo(new ArrayList<String>());
    }

    @Test
    void shouldReturnAnEmptyListIfNullable() {
        List<String> nullableList = null;

        ArrayUtils.moveToPosition(nullableList, "shouldBeOmitted", 1);

        Assertions
                .assertThat(nullableList)
                .isEqualTo(null);
    }

    @Test
    void shouldBeMovedToFirstPosition() {
        List<String> twoElementsList = new ArrayList<String>();
        twoElementsList.add("TOTO");
        twoElementsList.add("TATA");

        ArrayUtils.moveToPosition(twoElementsList, "TATA", 1);

        List<String> expectedList = List.of("TATA", "TOTO");

        Assertions
                .assertThat(twoElementsList)
                .isEqualTo(expectedList);
    }

    @Test
    void shouldBeMovedToLastPosition() {
        List<String> twoElementsList = new ArrayList<String>();
        twoElementsList.add("TOTO");
        twoElementsList.add("TATA");
        twoElementsList.add("TITI");

        ArrayUtils.moveToPosition(twoElementsList, "TOTO", 3);

        List<String> expectedList = List.of("TATA", "TITI", "TOTO");

        Assertions
                .assertThat(twoElementsList)
                .isEqualTo(expectedList);
    }

    @Test
    void shouldBeMovedToForthPosition() {
        List<String> twoElementsList = new ArrayList<String>();
        twoElementsList.add("TOTO");
        twoElementsList.add("TATA");
        twoElementsList.add("TITI");
        twoElementsList.add("TUTU");
        twoElementsList.add("TETE");

        ArrayUtils.moveToPosition(twoElementsList, "TATA", 4);

        List<String> expectedList = List.of("TOTO", "TITI", "TUTU", "TATA", "TETE");

        Assertions
                .assertThat(twoElementsList)
                .isEqualTo(expectedList);
    }

}
