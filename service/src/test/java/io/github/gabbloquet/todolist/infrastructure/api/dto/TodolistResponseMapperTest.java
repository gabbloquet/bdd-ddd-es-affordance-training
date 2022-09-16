package io.github.gabbloquet.todolist.infrastructure.api.dto;

import io.github.gabbloquet.todolist.domain.model.Todolist;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.TodolistResponse;
import io.github.gabbloquet.todolist.infrastructure.api.dto.todolist.TodolistResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TodolistResponseMapperTest {

    @InjectMocks
    private TodolistResponseMapper todolistResponseMapper;

    @Test
    public void has_self_relation_with_todolist() {
        // given
        Todolist todolist = new Todolist(List.of());

        // when
        TodolistResponse todolistResponse = todolistResponseMapper.map(todolist);

        // then
        Link actualLink = todolistResponse.getRequiredLink("self");

        // then
        assertThat(actualLink.getHref())
                .isEqualTo("/todolist");
    }

}