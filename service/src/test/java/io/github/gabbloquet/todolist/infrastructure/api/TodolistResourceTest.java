package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.todolist.TodolistService;
import io.github.gabbloquet.todolist.domain.todolist.deprioritizeTask.DeprioritizeTask;
import io.github.gabbloquet.todolist.domain.todolist.infra.dto.PrioritizeTaskRequest;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist;
import io.github.gabbloquet.todolist.domain.todolist.model.Todolist.Task;
import io.github.gabbloquet.todolist.domain.todolist.openApplication.OpenApplication;
import io.github.gabbloquet.todolist.domain.todolist.prioritizeTask.PrioritizeTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static io.github.gabbloquet.todolist.TestUtils.asJsonString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodolistResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodolistService todolistService;

    private final Task task = new Task("Practice TDD");
    private final Task anotherTask = new Task("Practice Simple Design");

    @BeforeEach
    public void setUp() {
        ArrayList<Task> listWithATask = new ArrayList<>(List.of(task));
        ArrayList<Task> listWithTwoTasks = new ArrayList<>(List.of(anotherTask, task));

        Todolist todolistWithOneTask = new Todolist(listWithATask);
        Todolist todolistWithTwoTasks = new Todolist(listWithTwoTasks);

        when(todolistService.execute(OpenApplication.builder().build()))
                .thenReturn(todolistWithOneTask);
        when(todolistService.execute(any(PrioritizeTask.class)))
                .thenReturn(todolistWithTwoTasks);
        when(todolistService.execute(any(DeprioritizeTask.class)))
                .thenReturn(todolistWithTwoTasks);
    }

    @Test
    public void get_todolist_contains_its_affordance() throws Exception {
        ResultActions requestResult = executeGetRequest();

        assertTodolistAffordance(requestResult);
    }

    @Test
    public void get_todolist_contains_tasks_affordance() throws Exception {
        String taskId = task.taskId().toString();

        executeGetRequest()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks[0].id").value(taskId))
                .andExpect(jsonPath("$.tasks[0].description").value("Practice TDD"))

                .andExpect(jsonPath("$.tasks[0]._links.deleteOrModifyTask.href", is("http://localhost/tasks/" + taskId)))
                .andExpect(jsonPath("$.tasks[0]._links.deleteOrModifyTask.title", is("Modify or delete a task")))

                .andExpect(jsonPath("$.tasks[0]._templates.default.method", is("PUT")))
                .andExpect(jsonPath("$.tasks[0]._templates.default.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[0]._templates.default.properties[0].type", is("text")))

                .andExpect(jsonPath("$.tasks[0]._templates.deleteTask.method", is("DELETE")))
                .andExpect(jsonPath("$.tasks[0]._templates.deleteTask.target", is("http://localhost/tasks/" + taskId)))

                .andExpect(jsonPath("$.tasks[0]._links.addTask.href", is("http://localhost/tasks")))
                .andExpect(jsonPath("$.tasks[0]._links.addTask.title", is("Add a task")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.method", is("POST")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.target", is("http://localhost/tasks")));
    }

    @Test
    public void prioritize_task_in_todolist_contains_todolist_affordances() throws Exception {
        ResultActions requestResult = executePrioritizeRequest();

        assertTodolistAffordance(requestResult);
    }

    @Test
    public void prioritize_task_in_todolist_contains_tasks_affordances() throws Exception {
        String taskId = task.taskId().toString();

        assertTasksAffordances(executePrioritizeRequest(), taskId);
    }

    @Test
    public void deprioritize_task_in_todolist_contains_todolist_affordances() throws Exception {
        ResultActions requestResult = executeDeprioritizeRequest();

        assertTodolistAffordance(requestResult);
    }

    @Test
    public void deprioritize_task_in_todolist_contains_tasks_affordances() throws Exception {
        String taskId = task.taskId().toString();

        assertTasksAffordances(executeDeprioritizeRequest(), taskId);
    }

    private ResultActions executeGetRequest() throws Exception {
        return mockMvc.perform(get("/todolist")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

    private ResultActions executePrioritizeRequest() throws Exception {
        return mockMvc.perform(post("/todolist/prioritize/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new PrioritizeTaskRequest(task.taskId())))
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

    private ResultActions executeDeprioritizeRequest() throws Exception {
        return mockMvc.perform(post("/todolist/deprioritize/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new PrioritizeTaskRequest(anotherTask.taskId())))
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

    private void assertTodolistAffordance(ResultActions request) throws Exception {
        request
            .andExpect(status().isOk())
            .andExpect(jsonPath("$._links.self.href", is("http://localhost/todolist")))
            .andExpect(jsonPath("$._links.self.title", is("Get todolist")))

            .andExpect(jsonPath("$._links.prioritizeTask.href", is("http://localhost/todolist/prioritize/task")))
            .andExpect(jsonPath("$._links.prioritizeTask.title", is("Prioritize a task")))
            .andExpect(jsonPath("$._links.prioritizeTask.name", is("default")))
            .andExpect(jsonPath("$._templates.default.properties[0].name", is("id")))
            .andExpect(jsonPath("$._templates.default.properties[0].readOnly", is(true)))
            .andExpect(jsonPath("$._templates.default.method", is("POST")))
            .andExpect(jsonPath("$._templates.default.target", is("http://localhost/todolist/prioritize/task")))

            .andExpect(jsonPath("$._links.deprioritizeTask.href", is("http://localhost/todolist/deprioritize/task")))
            .andExpect(jsonPath("$._links.deprioritizeTask.title", is("Deprioritize a task")))
            .andExpect(jsonPath("$._links.deprioritizeTask.name", is("deprioritize")))
            .andExpect(jsonPath("$._templates.default.properties[0].name", is("id")))
            .andExpect(jsonPath("$._templates.default.properties[0].readOnly", is(true)))
            .andExpect(jsonPath("$._templates.deprioritize.method", is("POST")))
            .andExpect(jsonPath("$._templates.deprioritize.target", is("http://localhost/todolist/deprioritize/task")));
    }

    private void assertTasksAffordances(ResultActions request, String taskId) throws Exception {
        request
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks[1].id").value(taskId))
                .andExpect(jsonPath("$.tasks[1].description").value("Practice TDD"))

                .andExpect(jsonPath("$.tasks[1]._links.deleteOrModifyTask.href", is("http://localhost/tasks/" + taskId)))
                .andExpect(jsonPath("$.tasks[1]._links.deleteOrModifyTask.title", is("Modify or delete a task")))

                .andExpect(jsonPath("$.tasks[1]._templates.default.method", is("PUT")))
                .andExpect(jsonPath("$.tasks[1]._templates.default.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[1]._templates.default.properties[0].type", is("text")))

                .andExpect(jsonPath("$.tasks[1]._templates.deleteTask.method", is("DELETE")))
                .andExpect(jsonPath("$.tasks[1]._templates.deleteTask.target", is("http://localhost/tasks/" + taskId)))

                .andExpect(jsonPath("$.tasks[1]._links.addTask.href", is("http://localhost/tasks")))
                .andExpect(jsonPath("$.tasks[1]._links.addTask.title", is("Add a task")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.method", is("POST")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.target", is("http://localhost/tasks")));
    }

}
