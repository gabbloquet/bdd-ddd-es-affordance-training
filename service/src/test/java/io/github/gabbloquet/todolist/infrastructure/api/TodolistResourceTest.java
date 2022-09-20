package io.github.gabbloquet.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.domain.InPort.TodolistService;
import io.github.gabbloquet.todolist.domain.model.Task;
import io.github.gabbloquet.todolist.domain.model.Todolist;
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

    @BeforeEach
    public void setUp() {
        Task task = new Task(0, "Practice TDD");
        Task anotherTask = new Task(1,"Practice Simple Design");

        List<Task> listWithATask = new ArrayList<>(List.of(task));
        List<Task> listWithTwoTasks = new ArrayList<>(List.of(anotherTask, task));

        Todolist todolistWithOneTask = new Todolist(listWithATask);
        Todolist todolistWithTwoTasks = new Todolist(listWithTwoTasks);

        when(todolistService.get())
                .thenReturn(todolistWithOneTask);
        when(todolistService.move(0, 2))
                .thenReturn(todolistWithTwoTasks);
    }

    @Test
    public void get_todolist() throws Exception {
        executeGetRequest()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks[0]").value("Practice TDD"));
    }

//    @Test
//    public void move_todolist() throws Exception {
//        executeMoveRequest()
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.tasks[0]").value("Practice Simple Design"))
//                .andExpect(jsonPath("$.tasks[1]").value("Practice TDD"));
//    }

    private ResultActions executeGetRequest() throws Exception {
        return mockMvc.perform(get("/todolist")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

    private ResultActions executeMoveRequest() throws Exception {
        return mockMvc.perform(put("/todolist/move/tasks")
                .content(asJsonString(new MoveTaskRequest(0, 2)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

}