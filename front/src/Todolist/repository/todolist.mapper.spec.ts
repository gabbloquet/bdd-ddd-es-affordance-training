import { toTodolist } from './todolist.mapper';
import { emptyDtoTodolist, TodolistResource } from './todolist.dtos';
import { taskCreatedDtoExample } from '../../Task/repository/task.dto';
import { taskCreated } from '../../Task/model/task.model';
import { HTTP_METHOD, ResourceLink, ResourceTemplate } from '../../shared/types/hateoas.types';

describe('Todolist mapper', () => {
  it('returns empty todolist', () => {
    // Given
    const todoListResource: TodolistResource = {
      _links: emptyDtoTodolist._links,
      _templates: emptyDtoTodolist._templates,
      tasks: []
    };

    // When
    const todolist = toTodolist(todoListResource);

    // Then
    expect(todolist.tasks).toHaveLength(0);
  });

  it('returns mapped task', () => {
    // Given
    const todoListResource: TodolistResource = {
      _links: emptyDtoTodolist._links,
      _templates: emptyDtoTodolist._templates,
      tasks: [taskCreatedDtoExample]
    };

    // When
    const todolist = toTodolist(todoListResource);

    // Then
    expect(todolist.tasks[0]).toStrictEqual(taskCreated);
  });

  it('returns prioritize task action', () => {
    // Given
    const todoListResource: TodolistResource = {
      _links: emptyDtoTodolist._links,
      _templates: emptyDtoTodolist._templates,
      tasks: []
    };

    // When
    const todolist = toTodolist(todoListResource);

    // Then
    const expectedAction = {
      method: HTTP_METHOD.POST,
      name: 'Prioritize',
      url: 'http://localhost:8080/todolist/prioritize/task',
      properties: [
        {
          name: 'id'
        }
      ]
    };
    expect(todolist.actions[0]).toStrictEqual(expectedAction);
  });

  it('returns deprioritize task action', () => {
    // Given
    const todoListResource: TodolistResource = {
      _links: emptyDtoTodolist._links,
      _templates: emptyDtoTodolist._templates,
      tasks: []
    };

    // When
    const todolist = toTodolist(todoListResource);

    // Then
    const expectedAction = {
      method: HTTP_METHOD.POST,
      name: 'Deprioritize',
      url: 'http://localhost:8080/todolist/deprioritize/task',
      properties: [
        {
          name: 'id'
        }
      ]
    };
    expect(todolist.actions[1]).toStrictEqual(expectedAction);
  });

  it('throws if links are undefined', () => {
    // Given
    const todoListResource: TodolistResource = {
      _links: {},
      _templates: emptyDtoTodolist._templates,
      tasks: []
    };

    // When
    const t = () => {
      const todolist = toTodolist(todoListResource);
    };

    expect(t).toThrow(TypeError);
    expect(t).toThrow('Links (_links) are missing in Resource.');
  });

  it('throws if templates are undefined', () => {
    // Given
    const todoListResource: TodolistResource = {
      _links: emptyDtoTodolist._links,
      _templates: {},
      tasks: []
    };

    // When
    const t = () => {
      const todolist = toTodolist(todoListResource);
    };

    expect(t).toThrow(TypeError);
    expect(t).toThrow('Templates (_templates) are missing in Resource.');
  });

  it('throws if prioritize template is undefined', () => {
    // Given
    const todoListResource: TodolistResource = {
      _links: emptyDtoTodolist._links,
      _templates: {
        ...emptyDtoTodolist._templates,
        default: {} as ResourceTemplate
      },
      tasks: []
    };

    // When
    const t = () => {
      const todolist = toTodolist(todoListResource);
    };

    expect(t).toThrow(TypeError);
    expect(t).toThrow('Prioritize (default) template is missing.');
  });

  it('throws if deprioritize template is undefined', () => {
    // Given
    const todoListResource: TodolistResource = {
      _links: emptyDtoTodolist._links,
      _templates: {
        ...emptyDtoTodolist._templates,
        deprioritize: {} as ResourceTemplate
      },
      tasks: []
    };

    // When
    const t = () => {
      const todolist = toTodolist(todoListResource);
    };

    expect(t).toThrow(TypeError);
    expect(t).toThrow('Deprioritize (deprioritize) template is missing.');
  });
});
