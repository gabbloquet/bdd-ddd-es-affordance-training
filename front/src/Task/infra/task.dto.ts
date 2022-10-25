import { Resource } from '../../shared/types/hateoas.types';

export interface TaskResource extends Resource {
  id: string;
  description: string;
  completed: boolean;
  creationTime: string;
  duration: string | null;
}

export const taskCreatedDtoExample: TaskResource = {
  id: '1a8d707e-506a-4923-a688-66cbc5674f14',
  description: 'Allé fumé du canon avec Gégé',
  completed: false,
  creationTime: '2022-09-16T05:10:00.000Z',
  duration: null,
  _links: {
    getOrDeleteTask: {
      href: 'http://localhost/tasks/1a8d707e-506a-4923-a688-66cbc5674f14',
      title: 'Get or delete a task'
    },
    deleteTask: {
      href: 'http://localhost/tasks/1a8d707e-506a-4923-a688-66cbc5674f14',
      title: 'Delete a task'
    },
    renameTask: {
      href: 'http://localhost/tasks/1a8d707e-506a-4923-a688-66cbc5674f14/rename',
      title: 'Rename a task'
    },
    completeTask: {
      href: 'http://localhost/tasks/1a8d707e-506a-4923-a688-66cbc5674f14/complete',
      title: 'Complete a task'
    },
    todolist: {
      href: 'http://localhost/todolist',
      title: 'Get todolist',
      name: 'Get all tasks, todolist'
    },
    prioritize: {
      href: 'http://localhost/todolist/prioritize/task',
      title: 'Prioritize a task',
      name: 'Prioritize'
    },
    deprioritize: {
      href: 'http://localhost/todolist/deprioritize/task',
      title: 'Deprioritize a task',
      name: 'Deprioritize'
    }
  },
  _templates: {
    completeTask: {
      method: 'PUT',
      properties: [],
      target: 'http://localhost/tasks/1a8d707e-506a-4923-a688-66cbc5674f14/complete'
    },
    default: {
      method: 'DELETE',
      properties: [],
      target: 'http://localhost/tasks/1a8d707e-506a-4923-a688-66cbc5674f14'
    },
    renameTask: {
      method: 'PUT',
      properties: [
        {
          name: 'description',
          readOnly: true,
          type: 'text'
        }
      ],
      target: 'http://localhost/tasks/1a8d707e-506a-4923-a688-66cbc5674f14/rename'
    },
    deleteTask: {
      method: 'DELETE',
      properties: [],
      target: 'http://localhost/tasks/1a8d707e-506a-4923-a688-66cbc5674f14'
    },
    prioritize: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: 'http://localhost/todolist/prioritize/task'
    },
    deprioritize: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: 'http://localhost/todolist/deprioritize/task'
    }
  }
};

export const anotherTaskDtoExample: TaskResource = {
  id: '91f66717-1899-46ba-b398-eaaac31b3cd4',
  description: "Saquer àl'pec avec chgros",
  completed: false,
  creationTime: '2022-10-15T09:39:22.887334531',
  duration: null,
  _links: {
    getOrDeleteTask: {
      href: 'http://localhost/tasks/750826f7-1950-4346-945e-de3c865b9449',
      title: 'Get or delete a task'
    },
    deleteTask: {
      href: 'http://localhost/tasks/750826f7-1950-4346-945e-de3c865b9449',
      title: 'Delete a task'
    },
    renameTask: {
      href: 'http://localhost/tasks/750826f7-1950-4346-945e-de3c865b9449/rename',
      title: 'Rename a task'
    },
    completeTask: {
      href: 'http://localhost/tasks/750826f7-1950-4346-945e-de3c865b9449/complete',
      title: 'Complete a task'
    },
    todolist: {
      href: 'http://localhost/todolist',
      title: 'Get todolist',
      name: 'Get all tasks, todolist'
    },
    prioritize: {
      href: 'http://localhost/todolist/prioritize/task',
      title: 'Prioritize a task',
      name: 'Prioritize'
    },
    deprioritize: {
      href: 'http://localhost/todolist/deprioritize/task',
      title: 'Deprioritize a task',
      name: 'Deprioritize'
    }
  },
  _templates: {
    completeTask: {
      method: 'PUT',
      properties: [],
      target: 'http://localhost/tasks/750826f7-1950-4346-945e-de3c865b9449/complete'
    },
    default: {
      method: 'DELETE',
      properties: [],
      target: 'http://localhost/tasks/750826f7-1950-4346-945e-de3c865b9449'
    },
    renameTask: {
      method: 'PUT',
      properties: [
        {
          name: 'description',
          readOnly: true,
          type: 'text'
        }
      ],
      target: 'http://localhost/tasks/750826f7-1950-4346-945e-de3c865b9449/rename'
    },
    deleteTask: {
      method: 'DELETE',
      properties: [],
      target: 'http://localhost/tasks/750826f7-1950-4346-945e-de3c865b9449'
    },
    prioritize: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: 'http://localhost/todolist/prioritize/task'
    },
    deprioritize: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: 'http://localhost/todolist/deprioritize/task'
    }
  }
};
