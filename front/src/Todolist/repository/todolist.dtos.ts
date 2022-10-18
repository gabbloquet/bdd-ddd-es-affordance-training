import {
  anotherTaskDtoExample,
  taskCreatedDtoExample,
  TaskResource
} from '../../Task/repository/task.dto';
import { Resource } from '../../shared/types/hateoas.types';

export interface TodolistResource extends Resource {
  tasks: Array<TaskResource>;
}

export const emptyDtoTodolist = {
  tasks: [],
  _links: {
    self: {
      href: 'http://localhost:8080/todolist',
      title: 'Get todolist'
    },
    prioritizeTask: {
      href: 'http://localhost:8080/todolist/prioritize/task',
      title: 'Prioritize a task',
      name: 'default'
    },
    deprioritizeTask: {
      href: 'http://localhost:8080/todolist/deprioritize/task',
      title: 'Deprioritize a task',
      name: 'deprioritize'
    }
  },
  _templates: {
    default: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: 'http://localhost:8080/todolist/prioritize/task'
    },
    deprioritize: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: 'http://localhost:8080/todolist/deprioritize/task'
    }
  }
};

export const todolistDtoWithTwoTasks = {
  tasks: [taskCreatedDtoExample, anotherTaskDtoExample],
  _links: {
    self: {
      href: 'http://localhost:8080/todolist',
      title: 'Get todolist'
    },
    prioritizeTask: {
      href: 'http://localhost:8080/todolist/prioritize/task',
      title: 'Prioritize a task',
      name: 'default'
    },
    deprioritizeTask: {
      href: 'http://localhost:8080/todolist/deprioritize/task',
      title: 'Deprioritize a task',
      name: 'deprioritize'
    }
  },
  _templates: {
    default: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: 'http://localhost:8080/todolist/prioritize/task'
    },
    deprioritize: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: 'http://localhost:8080/todolist/deprioritize/task'
    }
  }
};
