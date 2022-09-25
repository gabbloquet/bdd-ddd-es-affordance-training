package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.TaskId;
import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.MoveTaskRequest;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.gabbloquet.todolist.TestUtils.asJsonString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodolistResourceTest {

    @MockBean
    private TodolistService todolistService;

    @Autowired
    private MockMvc mockMvc;

    private final Task task = new Task("Practice TDD");
    private final Task anotherTask = new Task("Practice Simple Design");

    @BeforeEach
    public void setUp() {


        Map<TaskId, Task> mapWithATask = new HashMap<>(Map.of(task.id(), task));
        Map<TaskId, Task> mapWithTwoTasks = new HashMap<>(Map.of(anotherTask.id(), anotherTask, task.id(), task));

        Todolist todolistWithOneTask = new Todolist(mapWithATask);
        Todolist todolistWithTwoTasks = new Todolist(mapWithTwoTasks);

//        when(todolistService.get())
//                .thenReturn(todolistWithOneTask);
//        when(todolistService.move(0, 2))
//                .thenReturn(todolistWithTwoTasks);
    }

    @Test
    public void get_todolist_contains_its_affordance() throws Exception {
        executeGetRequest()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/todolist")))
                .andExpect(jsonPath("$._links.self.title", is("Get todolist")))

                .andExpect(jsonPath("$._links.moveTask.href", is("http://localhost/todolist/move/task")))
                .andExpect(jsonPath("$._links.moveTask.title", is("Move a task")))
                .andExpect(jsonPath("$._templates.default.method", is("PUT")))
                .andExpect(jsonPath("$._templates.default.properties[0].name", is("id")))
                .andExpect(jsonPath("$._templates.default.properties[0].type", is("number")))
                .andExpect(jsonPath("$._templates.default.properties[1].name", is("position")))
                .andExpect(jsonPath("$._templates.default.properties[1].type", is("number")))
                .andExpect(jsonPath("$._templates.default.target", is("http://localhost/todolist/move/task")));
    }

    @Test
    public void get_todolist_contains_tasks_affordance() throws Exception {
        executeGetRequest()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks[0].id").value("0"))
                .andExpect(jsonPath("$.tasks[0].description").value("Practice TDD"))

                .andExpect(jsonPath("$.tasks[0]._links.deleteOrModifyTask.href", is("http://localhost/tasks/0")))
                .andExpect(jsonPath("$.tasks[0]._links.deleteOrModifyTask.title", is("Modify or delete a task")))

                .andExpect(jsonPath("$.tasks[0]._templates.default.method", is("PUT")))
                .andExpect(jsonPath("$.tasks[0]._templates.default.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[0]._templates.default.properties[0].type", is("text")))

                .andExpect(jsonPath("$.tasks[0]._templates.deleteTask.method", is("DELETE")))
                .andExpect(jsonPath("$.tasks[0]._templates.deleteTask.target", is("http://localhost/tasks/0")))

                .andExpect(jsonPath("$.tasks[0]._links.addTask.href", is("http://localhost/tasks")))
                .andExpect(jsonPath("$.tasks[0]._links.addTask.title", is("Add a task")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.method", is("POST")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$.tasks[0]._templates.addTask.target", is("http://localhost/tasks")));
    }

    @Test
    public void move_todolist_contains_todolist_affordances() throws Exception {
        executeMoveRequest()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/todolist")))
                .andExpect(jsonPath("$._links.self.title", is("Get todolist")))

                .andExpect(jsonPath("$._links.moveTask.href", is("http://localhost/todolist/move/task")))
                .andExpect(jsonPath("$._links.moveTask.title", is("Move a task")))
                .andExpect(jsonPath("$._templates.default.method", is("PUT")))
                .andExpect(jsonPath("$._templates.default.properties[0].name", is("id")))
                .andExpect(jsonPath("$._templates.default.properties[0].type", is("number")))
                .andExpect(jsonPath("$._templates.default.properties[1].name", is("position")))
                .andExpect(jsonPath("$._templates.default.properties[1].type", is("number")))
                .andExpect(jsonPath("$._templates.default.target", is("http://localhost/todolist/move/task")));
    }

    @Test
    public void move_todolist_contains_tasks_affordances() throws Exception {
        executeMoveRequest()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks[1].id").value("0"))
                .andExpect(jsonPath("$.tasks[1].description").value("Practice TDD"))

                .andExpect(jsonPath("$.tasks[1]._links.deleteOrModifyTask.href", is("http://localhost/tasks/0")))
                .andExpect(jsonPath("$.tasks[1]._links.deleteOrModifyTask.title", is("Modify or delete a task")))

                .andExpect(jsonPath("$.tasks[1]._templates.default.method", is("PUT")))
                .andExpect(jsonPath("$.tasks[1]._templates.default.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[1]._templates.default.properties[0].type", is("text")))

                .andExpect(jsonPath("$.tasks[1]._templates.deleteTask.method", is("DELETE")))
                .andExpect(jsonPath("$.tasks[1]._templates.deleteTask.target", is("http://localhost/tasks/0")))

                .andExpect(jsonPath("$.tasks[1]._links.addTask.href", is("http://localhost/tasks")))
                .andExpect(jsonPath("$.tasks[1]._links.addTask.title", is("Add a task")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.method", is("POST")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.properties[0].name", is("description")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.properties[0].type", is("text")))
                .andExpect(jsonPath("$.tasks[1]._templates.addTask.target", is("http://localhost/tasks")));
    }

    private ResultActions executeGetRequest() throws Exception {
        return mockMvc.perform(get("/todolist")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

    private ResultActions executeMoveRequest() throws Exception {
        return mockMvc.perform(put("/todolist/move/task")
                .content(asJsonString(new MoveTaskRequest(0, 2)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

}
