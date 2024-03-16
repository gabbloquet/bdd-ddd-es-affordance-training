package io.github.gabbloquet.features.todolist.domain.specs;

import io.cucumber.java.fr.Quand;
import io.github.gabbloquet.features.todolist.domain.ScenarioState;
import io.github.gabbloquet.todolist.todolist.filter.TodolistQueries;
import org.springframework.beans.factory.annotation.Autowired;

import static io.github.gabbloquet.todolist.todolist.filter.TodolistQueries.Filter.COMPLETED_TASKS;
import static io.github.gabbloquet.todolist.todolist.filter.TodolistQueries.Filter.TO_DO_TASKS;

public class FilterTasksSpec {

    @Autowired
    private ScenarioState scenarioState;

    @Autowired
    private TodolistQueries todolistQueries;

    @Quand("les tâches en cours sont demandées")
    public void les_taches_en_cours_sont_demandees() {
        scenarioState.todolist = todolistQueries.filterBy(TO_DO_TASKS);
    }

    @Quand("les tâches terminées sont demandées")
    public void lesTâchesTerminéesSontDemandées() {
        scenarioState.todolist = todolistQueries.filterBy(COMPLETED_TASKS);
    }
}
