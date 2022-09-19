package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.InPort.TaskService;
import io.github.gabbloquet.todolist.domain.model.Task;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskResourceTest {

    @MockBean
    private TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        Task task = new Task(1, "Practice TDD");

        when(taskService.get(1))
                .thenReturn(task);
        when(taskService.modify(2, "Always practice TDD!"))
                .thenReturn(new Task(2, "Always practice TDD!"));
        when(taskService.add("Hey! Im a new task !"))
                .thenReturn(new Task(3, "Hey! Im a new task !"));
    }


    @Test
    public void get_a_task() throws Exception {
        executeGetTaskOneRequest()
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("description").value("Practice TDD"))

                .andExpect(jsonPath("$._links.deleteOrModifyTask.href", is("http://localhost/tasks/1")))
                .andExpect(jsonPath("$._links.deleteOrModifyTask.title", is("Modify or Delete a task")))

                .andExpect(jsonPath("$._templates.default.method", is("PUT")))
                .andExpect(jsonPath("$._templates.default.properties[0].name", is("description")))
                .andExpect(jsonPath("$._templates.default.properties[0].type", is("text")))

                .andExpect(jsonPath("$._templates.deleteTask.method", is("DELETE")))
                .andExpect(jsonPath("$._templates.deleteTask.target", is("http://localhost/tasks/1")))

                .andExpect(jsonPath("$._links.addTask.href", is("http://localhost/tasks")))
                .andExpect(jsonPath("$._links.addTask.title", is("Add a task")))
                .andExpect(jsonPath("$._templates.addTask.method", is("POST")))
                .andExpect(jsonPath("$._templates.addTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$._templates.addTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$._templates.addTask.target", is("http://localhost/tasks")))

                .andExpect(jsonPath("$._links.todolist.href", is("http://localhost/todolist")));
    }

    @Test
    public void modify_a_task() throws Exception {
        executeModifyTaskTwoRequest()
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(2))
                .andExpect(jsonPath("description").value("Always practice TDD!"))

                .andExpect(jsonPath("$._links.deleteOrModifyTask.href", is("http://localhost/tasks/2")))
                .andExpect(jsonPath("$._links.deleteOrModifyTask.title", is("Modify or Delete a task")))

                .andExpect(jsonPath("$._templates.default.method", is("PUT")))
                .andExpect(jsonPath("$._templates.default.properties[0].name", is("description")))
                .andExpect(jsonPath("$._templates.default.properties[0].type", is("text")))

                .andExpect(jsonPath("$._templates.deleteTask.method", is("DELETE")))
                .andExpect(jsonPath("$._templates.deleteTask.target", is("http://localhost/tasks/2")))

                .andExpect(jsonPath("$._links.addTask.href", is("http://localhost/tasks")))
                .andExpect(jsonPath("$._links.addTask.title", is("Add a task")))
                .andExpect(jsonPath("$._templates.addTask.method", is("POST")))
                .andExpect(jsonPath("$._templates.addTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$._templates.addTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$._templates.addTask.target", is("http://localhost/tasks")))

                .andExpect(jsonPath("$._links.todolist.href", is("http://localhost/todolist")));
    }

    @Test
    public void add_a_task() throws Exception {
        executeAddATaskRequest()
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(3))
                .andExpect(jsonPath("description").value("Hey! Im a new task !"))

                .andExpect(jsonPath("$._links.deleteOrModifyTask.href", is("http://localhost/tasks/3")))
                .andExpect(jsonPath("$._links.deleteOrModifyTask.title", is("Modify or Delete a task")))

                .andExpect(jsonPath("$._templates.default.method", is("PUT")))
                .andExpect(jsonPath("$._templates.default.properties[0].name", is("description")))
                .andExpect(jsonPath("$._templates.default.properties[0].type", is("text")))

                .andExpect(jsonPath("$._templates.deleteTask.method", is("DELETE")))
                .andExpect(jsonPath("$._templates.deleteTask.target", is("http://localhost/tasks/3")))

                .andExpect(jsonPath("$._links.addTask.href", is("http://localhost/tasks")))
                .andExpect(jsonPath("$._links.addTask.title", is("Add a task")))
                .andExpect(jsonPath("$._templates.addTask.method", is("POST")))
                .andExpect(jsonPath("$._templates.addTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$._templates.addTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$._templates.addTask.target", is("http://localhost/tasks")))

                .andExpect(jsonPath("$._links.todolist.href", is("http://localhost/todolist")));
    }

    @Test
    public void delete_a_task() throws Exception {
        TaskService spy = spy(taskService);
        doNothing().when(spy).delete(1);

        executeDeleteATaskRequest()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.addTask.href", is("http://localhost/tasks")))
                .andExpect(jsonPath("$._links.addTask.title", is("Add a task")))
                .andExpect(jsonPath("$._templates.default.method", is("POST")))
                .andExpect(jsonPath("$._templates.default.properties[0].name", is("description")))
                .andExpect(jsonPath("$._templates.default.properties[0].type", is("text")))
                .andExpect(jsonPath("$._templates.default.target", is("http://localhost/tasks")))

                .andExpect(jsonPath("$._links.todolist.href", is("http://localhost/todolist")));
    }


    private ResultActions executeGetTaskOneRequest() throws Exception {
        return mockMvc.perform(get("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

    private ResultActions executeModifyTaskTwoRequest() throws Exception {
        return mockMvc.perform(put("/tasks/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON)
                .content("{\"description\": \"Always practice TDD!\"}"));
    }

    private ResultActions executeAddATaskRequest() throws Exception {
        return mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON)
                .content("{\"description\": \"Hey! Im a new task !\"}"));
    }

    private ResultActions executeDeleteATaskRequest() throws Exception {
        return mockMvc.perform(delete("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

}