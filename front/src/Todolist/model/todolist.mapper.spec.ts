import { toTodolist } from './todolist.mapper';
import { TodolistResource } from './todolist.dtos';
import { Todolist } from '../model/todolist.model';
import { taskCreatedDtoExample } from '../../Task/repository/task.dto';

describe('Todolist mapper', () => {
  it('returns empty todolist', () => {
    // Given
    const todoListResource: TodolistResource = {
      tasks: [],
      _links: {},
      _templates: {}
    };

    // When
    const todolist = toTodolist(todoListResource);

    // Then
    const expectedTodolist: Todolist = {
      tasks: []
    };
    expect(todolist).toStrictEqual(expectedTodolist);
  });
  it('returns mapped task', () => {
    // Given
    const todoListResource: TodolistResource = {
      tasks: [
        {
          id: '1a8d707e-506a-4923-a688-66cbc5674f13',
          description: 'Allé fumé du canon avec Gégé',
          completed: false,
          creationTime: '2022-10-15T09:04:17.725932205',
          duration: null,
          _links: [],
          _templates: []
        }
      ],
      _links: {},
      _templates: {}
    };

    // When
    const todolist = toTodolist(todoListResource);

    // Then
    const expectedTodolist: Todolist = {
      tasks: [{}]
    };
    expect(todolist).toStrictEqual(expectedTodolist);
  });
});
