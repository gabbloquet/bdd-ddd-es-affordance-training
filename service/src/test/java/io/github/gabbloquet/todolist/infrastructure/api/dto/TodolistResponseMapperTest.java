package io.github.gabbloquet.todolist.infrastructure.api.dto;

import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.TodolistResponseMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodolistResponseMapperTest {

    @InjectMocks
    private TodolistResponseMapper todolistResponseMapper;

//    @Test
//    public void has_self_relation() {
//        // given
//        Todolist todolist = new Todolist(List.of());
//
//        // when
//        TodolistResponse todolistResponse = todolistResponseMapper.map(todolist);
//
//        // then
//        Link actualLink = todolistResponse.getRequiredLink("self");
//
//        // then
//        assertThat(actualLink.getHref())
//                .isEqualTo("/todolist");
//    }
//
//    @Test
//    public void has_relation_to_add_a_task() {
//        // given
//        Todolist todolist = new Todolist(List.of());
//
//        // when
//        TodolistResponse todolistResponse = todolistResponseMapper.map(todolist);
//
//        // then
//        Link actualLink = todolistResponse.getRequiredLink("tasks");
//
//        // then
//        assertThat(actualLink.getTitle())
//                .isEqualTo("Add a task");
//    }

}