import { toTodolist } from './todolist.mapper';
import { TodolistResource } from './todolist.dtos';
import { Todolist, TODOLIST_ACTIONS } from '../model/todolist.model';
import { taskCreatedDtoExample } from '../../Task/repository/task.dto';
import { taskCreated } from '../../Task/model/task.model';
import { HTTP_METHOD, ResourceLink, ResourceTemplate } from '../../shared/types/hateoas.types';

describe('Todolist mapper', () => {
  it('returns empty todolist', () => {
    // Given
    const todoListResource: TodolistResource = {
      tasks: []
    };

    // When
    const todolist = toTodolist(todoListResource);

    // Then
    const expectedTodolist: Todolist = {
      tasks: [],
      actions: []
    };
    expect(todolist).toStrictEqual(expectedTodolist);
  });

  it('returns mapped task', () => {
    // Given
    const todoListResource: TodolistResource = {
      tasks: [taskCreatedDtoExample]
    };

    // When
    const todolist = toTodolist(todoListResource);

    // Then
    const expectedTodolist: Todolist = {
      tasks: [taskCreated],
      actions: []
    };
    expect(todolist).toStrictEqual(expectedTodolist);
  });

  it('returns prioritize task action', () => {
    // Given
    const todoListResource: TodolistResource = {
      tasks: [],
      _links: new Map<string, ResourceLink>().set('prioritizeTask', {
        href: 'http://localhost:8080/todolist/prioritize/task',
        title: 'Prioritize a task',
        name: 'default'
      }),
      _templates: new Map<string, ResourceTemplate>().set('default', {
        method: 'POST',
        properties: [
          {
            name: 'id',
            readOnly: true
          }
        ],
        target: 'http://localhost:8080/todolist/prioritize/task'
      })
    };

    // When
    const todolist = toTodolist(todoListResource);

    // Then
    const expectedTodolist: Todolist = {
      tasks: [],
      actions: [
        {
          method: HTTP_METHOD.POST,
          type: TODOLIST_ACTIONS.PRIORITIZE_TASK,
          url: new URL('http://localhost:8080/todolist/prioritize/task'),
          properties: [
            {
              name: 'id'
            }
          ]
        }
      ]
    };
    expect(todolist).toStrictEqual(expectedTodolist);
  });
});
