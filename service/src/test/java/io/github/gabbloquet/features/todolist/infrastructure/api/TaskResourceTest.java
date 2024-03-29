package io.github.gabbloquet.features.todolist.infrastructure.api;

import io.github.gabbloquet.todolist.task.TaskService;
import io.github.gabbloquet.todolist.task.addTask.AddTask;
import io.github.gabbloquet.todolist.task.addTask.TaskCreated;
import io.github.gabbloquet.todolist.task.completeTask.CompleteTask;
import io.github.gabbloquet.todolist.task.completeTask.TaskCompleted;
import io.github.gabbloquet.todolist.task.deleteTask.DeleteTask;
import io.github.gabbloquet.todolist.task.model.TaskId;
import io.github.gabbloquet.todolist.task.model.TaskState;
import io.github.gabbloquet.todolist.task.renameTask.RenameTask;
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
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private final UUID uuid = UUID.randomUUID();
    private final TaskId taskId = TaskId.from(uuid);
    private final String id = uuid.toString();
    private final LocalDateTime creationTime = LocalDateTime.of(2022, 10, 1, 6, 0);
    private final LocalDateTime completedTime = LocalDateTime.of(2022, 10, 11, 6, 0);

    private final TaskState taskState = new TaskState(
            List.of(TaskCreated.builder()
                    .taskId(taskId)
                    .description("Practice TDD")
                    .creationTime(creationTime)
                    .build())
    );

    private final TaskState modifiedTaskState = new TaskState(
            List.of(TaskCreated.builder()
                    .taskId(taskId)
                    .description("Always practice TDD!")
                    .creationTime(creationTime)
                    .build())
    );

    private final TaskState addedTaskState = new TaskState(
            List.of(TaskCreated.builder()
                    .taskId(taskId)
                    .description("Hey! Im a new task !")
                    .creationTime(creationTime)
                    .build())
    );

    private final TaskState completedTaskState = new TaskState(
            List.of(TaskCreated.builder()
                            .taskId(taskId)
                            .description("Practice TDD")
                            .creationTime(creationTime)
                            .build(),
                    TaskCompleted.builder()
                            .taskId(taskId)
                            .at(completedTime)
                            .build()
            )
    );

    @BeforeEach
    public void setUp() {
        when(taskService.getTask(uuid))
                .thenReturn(taskState);
        when(taskService.execute(AddTask.builder().description("Hey! Im a new task !").build()))
                .thenReturn(addedTaskState);
        when(taskService.execute(CompleteTask.builder().taskId(taskState.id()).build()))
                .thenReturn(completedTaskState);
        when(taskService.execute(RenameTask.builder().taskId(taskState.id()).update("Always practice TDD!").build()))
                .thenReturn(modifiedTaskState);
    }

    @Test
    public void get_a_task() throws Exception {
        ResultActions requestResult = executeGetTaskOneRequest();

        assertTaskAffordance(requestResult, "Practice TDD");

        requestResult
                .andExpect(jsonPath("completed").value(false));
    }

    @Test
    public void rename_a_task() throws Exception {
        ResultActions requestResult = executeRenameTaskRequest();

        assertTaskAffordance(requestResult, "Always practice TDD!");

        requestResult
                .andExpect(jsonPath("completed").value(false));
    }

    @Test
    public void complete_a_task() throws Exception {
        ResultActions requestResult = executeCompleteTaskRequest();

        assertTaskAffordance(requestResult, "Practice TDD");

        requestResult
                .andExpect(jsonPath("completed").value(true))
                .andExpect(jsonPath("duration").value("10 jour(s)"));
    }

    @Test
    public void delete_a_task_do_nothing() throws Exception {
        executeDeleteATaskRequest()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.todolist.href", is("http://localhost/todolist")))
                .andExpect(jsonPath("$._links.todolist.name", is("Get all tasks, todolist")))
                .andExpect(jsonPath("$._links.todolist.title", is("Get todolist")));

        verify(taskService).execute(DeleteTask.builder().taskId(taskId).build());
    }

    public void assertTaskAffordance(ResultActions request, String description) throws Exception {
        request
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("description").value(description))
                .andExpect(jsonPath("creationTime").value("2022-10-01T06:00:00"))

                .andExpect(jsonPath("$._links.default.href", is("http://localhost/tasks/" + id)))
                .andExpect(jsonPath("$._links.default.title", is("Delete a task")))
                .andExpect(jsonPath("$._links.default.name", is("Delete")))
                .andExpect(jsonPath("$._templates.default.method", is("DELETE")))
                .andExpect(jsonPath("$._templates.default.target", is("http://localhost/tasks/" + id)))

                .andExpect(jsonPath("$._links.rename.href", is("http://localhost/tasks/" + id + "/rename")))
                .andExpect(jsonPath("$._templates.rename.method", is("PUT")))
                .andExpect(jsonPath("$._templates.rename.properties[0].name", is("description")))
                .andExpect(jsonPath("$._templates.rename.properties[0].type", is("text")))
                .andExpect(jsonPath("$._templates.rename.target", is("http://localhost/tasks/" + id + "/rename")))

                .andExpect(jsonPath("$._links.complete.href", is("http://localhost/tasks/" + id + "/complete")))
                .andExpect(jsonPath("$._links.complete.title", is("Complete a task")))
                .andExpect(jsonPath("$._links.complete.name", is("Complete")))
                .andExpect(jsonPath("$._templates.complete.method", is("PUT")))
                .andExpect(jsonPath("$._templates.complete.target", is("http://localhost/tasks/" + id + "/complete")));
    }


    private ResultActions executeGetTaskOneRequest() throws Exception {
        return mockMvc.perform(get("/tasks/" + uuid)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

    private ResultActions executeRenameTaskRequest() throws Exception {
        return mockMvc.perform(put("/tasks/" + uuid + "/rename")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON)
                .content("{\"description\": \"Always practice TDD!\"}"));
    }

    private ResultActions executeCompleteTaskRequest() throws Exception {
        return mockMvc.perform(put("/tasks/" + uuid + "/complete")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

    private ResultActions executeDeleteATaskRequest() throws Exception {
        return mockMvc.perform(delete("/tasks/" + uuid)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON));
    }

}
