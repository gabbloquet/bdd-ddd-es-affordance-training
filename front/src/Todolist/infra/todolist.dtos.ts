import {
  anotherTaskDtoExample,
  taskCreatedDtoExample,
  TaskResource
} from '../../Task/infra/task.dto';
import { Resource } from '../../shared/types/hateoas.types';

export interface TodolistResource extends Resource {
  tasks: Array<TaskResource>;
}

export const SERVICE_HREF = 'http://localhost:8080';

export const emptyDtoTodolist: TodolistResource = {
  tasks: [],
  _links: {
    self: {
      href: `${SERVICE_HREF}/todolist`,
      title: 'Get todolist'
    },
    default: {
      href: `${SERVICE_HREF}/todolist/add/task`,
      title: 'Add a task',
      name: 'Add a task'
    }
  },
  _templates: {
    default: {
      method: 'POST',
      properties: [
        {
          name: 'description',
          readOnly: true,
          type: 'text'
        }
      ],
      target: `${SERVICE_HREF}/todolist/add/task`
    }
  }
};

export const todolistDtoWithTwoTasks: TodolistResource = {
  tasks: [taskCreatedDtoExample, anotherTaskDtoExample],
  _links: {
    self: {
      href: `${SERVICE_HREF}/todolist`,
      title: 'Get todolist'
    },
    default: {
      href: `${SERVICE_HREF}/todolist/add/task`,
      title: 'Add a task',
      name: 'Add a task'
    }
  },
  _templates: {
    default: {
      method: 'POST',
      properties: [
        {
          name: 'description',
          readOnly: true,
          type: 'text'
        }
      ],
      target: `${SERVICE_HREF}/todolist/add/task`
    }
  }
};
