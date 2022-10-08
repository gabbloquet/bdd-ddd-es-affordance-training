package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Quand;
import io.github.gabbloquet.todolist.domain.todolist.filter.TodolistQueries;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static io.github.gabbloquet.todolist.domain.todolist.filter.TodolistQueries.Filter.COMPLETED_TASKS;
import static io.github.gabbloquet.todolist.domain.todolist.filter.TodolistQueries.Filter.TO_DO_TASKS;

public class FilterTasksSpec {

    private Todolist todolist;

    @Autowired
    private TodolistQueries todolistQueries;

    @Quand("les tâches en cours sont demandées")
    public void les_taches_en_cours_sont_demandees() {
        todolist = todolistQueries.filterBy(TO_DO_TASKS);
    }

    @Quand("les tâches terminées sont demandées")
    public void lesTâchesTerminéesSontDemandées() {
        todolist = todolistQueries.filterBy(COMPLETED_TASKS);
    }
}
