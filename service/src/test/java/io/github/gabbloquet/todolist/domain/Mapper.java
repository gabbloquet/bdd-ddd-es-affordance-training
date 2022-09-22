package io.github.gabbloquet.todolist.domain;

import io.cucumber.java.ParameterType;
import io.github.gabbloquet.todolist.domain.model.Task;

public class Mapper {

    @ParameterType("\".*\"")
    public Task task(String task) {
        return new Task(task);
    }

}
