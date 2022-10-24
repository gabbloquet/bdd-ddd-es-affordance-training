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

import java.time.LocalDateTime;
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

    private final Task task = new Task("Practice TDD", LocalDateTime.of(2022, 8, 1, 8, 20));
    private final Task anotherTask = new Task("Practice Simple Design", LocalDateTime.of(2022, 8, 2, 7, 30));

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
        String taskId = task.taskId().id().toString();
        ResultActions result = executeGetRequest();

        assertFirstTaskAffordances(result, taskId, "Practice TDD", "2022-08-01T08:20:00");
    }

    @Test
    public void prioritize_task_in_todolist_contains_todolist_affordances() throws Exception {
        ResultActions requestResult = executePrioritizeRequest();

        assertTodolistAffordance(requestResult);
    }

    @Test
    public void prioritize_task_in_todolist_contains_tasks_affordances() throws Exception {
        String firstTaskId = anotherTask.taskId().id().toString();
        String secondTaskId = task.taskId().id().toString();

        ResultActions result = executePrioritizeRequest();

        assertFirstTaskAffordances(result, firstTaskId, "Practice Simple Design", "2022-08-02T07:30:00");
        assertSecondTaskAffordances(result, secondTaskId, "Practice TDD", "2022-08-01T08:20:00");
    }

    @Test
    public void deprioritize_task_in_todolist_contains_todolist_affordances() throws Exception {
        ResultActions requestResult = executeDeprioritizeRequest();

        assertTodolistAffordance(requestResult);
    }

    @Test
    public void deprioritize_task_in_todolist_contains_tasks_affordances() throws Exception {
        String firstTaskId = anotherTask.taskId().id().toString();
        String secondTaskId = task.taskId().id().toString();

        ResultActions result = executeDeprioritizeRequest();

        assertFirstTaskAffordances(result, firstTaskId, "Practice Simple Design", "2022-08-02T07:30:00");
        assertSecondTaskAffordances(result, secondTaskId, "Practice TDD", "2022-08-01T08:20:00");
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

                .andExpect(jsonPath("$._links.default.href", is("http://localhost/todolist/prioritize/task")))
                .andExpect(jsonPath("$._links.default.title", is("Prioritize a task")))
                .andExpect(jsonPath("$._links.default.name", is("Prioritize")))
                .andExpect(jsonPath("$._templates.default.properties[0].name", is("id")))
                .andExpect(jsonPath("$._templates.default.properties[0].readOnly", is(true)))
                .andExpect(jsonPath("$._templates.default.method", is("POST")))
                .andExpect(jsonPath("$._templates.default.target", is("http://localhost/todolist/prioritize/task")))

                .andExpect(jsonPath("$._links.deprioritize.href", is("http://localhost/todolist/deprioritize/task")))
                .andExpect(jsonPath("$._links.deprioritize.title", is("Deprioritize a task")))
                .andExpect(jsonPath("$._links.deprioritize.name", is("Deprioritize")))
                .andExpect(jsonPath("$._templates.deprioritize.properties[0].name", is("id")))
                .andExpect(jsonPath("$._templates.deprioritize.properties[0].readOnly", is(true)))
                .andExpect(jsonPath("$._templates.deprioritize.method", is("POST")))
                .andExpect(jsonPath("$._templates.deprioritize.target", is("http://localhost/todolist/deprioritize/task")))

                .andExpect(jsonPath("$._links.addTask.href", is("http://localhost/tasks")))
                .andExpect(jsonPath("$._links.addTask.title", is("Add a task")))
                .andExpect(jsonPath("$._links.addTask.name", is("Add a task")))
                .andExpect(jsonPath("$._templates.addTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$._templates.addTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$._templates.addTask.method", is("POST")))
                .andExpect(jsonPath("$._templates.addTask.target", is("http://localhost/tasks")));
    }

    private void assertFirstTaskAffordances(ResultActions result, String taskId, String description, String date) throws Exception {
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks[0].id").value(taskId))
                .andExpect(jsonPath("$.tasks[0].description").value(description))
                .andExpect(jsonPath("$.tasks[0].creationTime").value(date))

                .andExpect(jsonPath("$.tasks[0]._links.getOrDeleteTask.href", is("http://localhost/tasks/" + taskId)))
                .andExpect(jsonPath("$.tasks[0]._links.getOrDeleteTask.title", is("Get or delete a task")))

                .andExpect(jsonPath("$.tasks[0]._links.deleteTask.href", is("http://localhost/tasks/" + taskId)))
                .andExpect(jsonPath("$.tasks[0]._links.deleteTask.title", is("Delete a task")))
                .andExpect(jsonPath("$.tasks[0]._templates.deleteTask.method", is("DELETE")))
                .andExpect(jsonPath("$.tasks[0]._templates.deleteTask.target", is("http://localhost/tasks/" + taskId)))
                .andExpect(jsonPath("$.tasks[0]._templates.default.method", is("DELETE")))
                .andExpect(jsonPath("$.tasks[0]._templates.default.target", is("http://localhost/tasks/" + taskId)))

                .andExpect(jsonPath("$.tasks[0]._links.renameTask.href", is("http://localhost/tasks/" + taskId + "/rename")))
                .andExpect(jsonPath("$.tasks[0]._templates.renameTask.method", is("PUT")))
                .andExpect(jsonPath("$.tasks[0]._templates.renameTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[0]._templates.renameTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$.tasks[0]._templates.renameTask.target", is("http://localhost/tasks/" + taskId + "/rename")))

                .andExpect(jsonPath("$.tasks[0]._links.completeTask.href", is("http://localhost/tasks/" + taskId + "/complete")))
                .andExpect(jsonPath("$.tasks[0]._templates.completeTask.method", is("PUT")))
                .andExpect(jsonPath("$.tasks[0]._templates.completeTask.target", is("http://localhost/tasks/" + taskId + "/complete")))

                .andExpect(jsonPath("$.tasks[0]._links.addTask.href", is("http://localhost/tasks")))
                .andExpect(jsonPath("$.tasks[0]._links.addTask.title", is("Add a task")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.method", is("POST")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.target", is("http://localhost/tasks")))
                .andExpect(jsonPath("$.tasks[0]._links.todolist.href", is("http://localhost/todolist")));
    }

    private void assertSecondTaskAffordances(ResultActions request, String taskId, String description, String date) throws Exception {
        request
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks[1].id").value(taskId))
                .andExpect(jsonPath("$.tasks[1].description").value(description))
                .andExpect(jsonPath("$.tasks[1].creationTime").value(date))

                .andExpect(jsonPath("$.tasks[1]._links.getOrDeleteTask.href", is("http://localhost/tasks/" + taskId)))
                .andExpect(jsonPath("$.tasks[1]._links.getOrDeleteTask.title", is("Get or delete a task")))

                .andExpect(jsonPath("$.tasks[1]._links.deleteTask.href", is("http://localhost/tasks/" + taskId)))
                .andExpect(jsonPath("$.tasks[1]._links.deleteTask.title", is("Delete a task")))
                .andExpect(jsonPath("$.tasks[1]._templates.deleteTask.method", is("DELETE")))
                .andExpect(jsonPath("$.tasks[1]._templates.deleteTask.target", is("http://localhost/tasks/" + taskId)))
                .andExpect(jsonPath("$.tasks[1]._templates.default.method", is("DELETE")))
                .andExpect(jsonPath("$.tasks[1]._templates.default.target", is("http://localhost/tasks/" + taskId)))

                .andExpect(jsonPath("$.tasks[1]._links.renameTask.href", is("http://localhost/tasks/" + taskId + "/rename")))
                .andExpect(jsonPath("$.tasks[1]._templates.renameTask.method", is("PUT")))
                .andExpect(jsonPath("$.tasks[1]._templates.renameTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[1]._templates.renameTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$.tasks[1]._templates.renameTask.target", is("http://localhost/tasks/" + taskId + "/rename")))

                .andExpect(jsonPath("$.tasks[1]._links.completeTask.href", is("http://localhost/tasks/" + taskId + "/complete")))
                .andExpect(jsonPath("$.tasks[1]._templates.completeTask.method", is("PUT")))
                .andExpect(jsonPath("$.tasks[1]._templates.completeTask.target", is("http://localhost/tasks/" + taskId + "/complete")))

                .andExpect(jsonPath("$.tasks[1]._links.addTask.href", is("http://localhost/tasks")))
                .andExpect(jsonPath("$.tasks[1]._links.addTask.title", is("Add a task")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.method", is("POST")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.target", is("http://localhost/tasks")))
                .andExpect(jsonPath("$.tasks[1]._links.todolist.href", is("http://localhost/todolist")));
    }

}
