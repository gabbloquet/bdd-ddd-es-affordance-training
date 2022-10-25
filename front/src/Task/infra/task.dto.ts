import { Resource } from '../../shared/types/hateoas.types';
import { SERVICE_HREF } from '../../Todolist/infra/todolist.dtos';

export interface TaskResource extends Resource {
  id: string;
  description: string;
  completed: boolean;
  creationTime: string;
  duration: string | null;
}

export const taskCreatedDtoExample: TaskResource = {
  id: '1531a79f-2f95-41ee-b5e5-98e1aec6a235',
  description: 'Allé fumé du canon avec Gégé',
  completed: false,
  creationTime: '2022-09-16T05:10:00.000Z',
  duration: null,
  _links: {
    default: {
      href: `${SERVICE_HREF}/tasks/1531a79f-2f95-41ee-b5e5-98e1aec6a235`,
      title: 'Delete a task',
      name: 'Delete'
    },
    rename: {
      href: `${SERVICE_HREF}/tasks/1531a79f-2f95-41ee-b5e5-98e1aec6a235/rename`,
      title: 'Rename a task'
    },
    complete: {
      href: `${SERVICE_HREF}/tasks/1531a79f-2f95-41ee-b5e5-98e1aec6a235/complete`,
      title: 'Complete a task',
      name: 'Complete'
    },
    todolist: {
      href: `${SERVICE_HREF}/todolist`,
      title: 'Get todolist',
      name: 'Get all tasks, todolist'
    },
    prioritize: {
      href: `${SERVICE_HREF}/todolist/prioritize/task`,
      title: 'Prioritize a task',
      name: 'Prioritize'
    },
    deprioritize: {
      href: `${SERVICE_HREF}/todolist/deprioritize/task`,
      title: 'Deprioritize a task',
      name: 'Deprioritize'
    }
  },
  _templates: {
    default: {
      method: 'DELETE',
      properties: [],
      target: `${SERVICE_HREF}/tasks/1531a79f-2f95-41ee-b5e5-98e1aec6a235`
    },
    rename: {
      method: 'PUT',
      properties: [
        {
          name: 'description',
          readOnly: true,
          type: 'text'
        }
      ],
      target: `${SERVICE_HREF}/tasks/1531a79f-2f95-41ee-b5e5-98e1aec6a235/rename`
    },
    complete: {
      method: 'PUT',
      properties: [],
      target: `${SERVICE_HREF}/tasks/1531a79f-2f95-41ee-b5e5-98e1aec6a235/complete`
    },
    prioritize: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: `${SERVICE_HREF}/todolist/prioritize/task`
    },
    deprioritize: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: `${SERVICE_HREF}/todolist/deprioritize/task`
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
    default: {
      href: `${SERVICE_HREF}/tasks/91f66717-1899-46ba-b398-eaaac31b3cd4`,
      title: 'Delete a task',
      name: 'Delete'
    },
    rename: {
      href: `${SERVICE_HREF}/tasks/91f66717-1899-46ba-b398-eaaac31b3cd4/rename`,
      title: 'Rename a task'
    },
    complete: {
      href: `${SERVICE_HREF}/tasks/91f66717-1899-46ba-b398-eaaac31b3cd4/complete`,
      title: 'Complete a task',
      name: 'Complete'
    },
    todolist: {
      href: `${SERVICE_HREF}/todolist`,
      title: 'Get todolist',
      name: 'Get all tasks, todolist'
    },
    prioritize: {
      href: `${SERVICE_HREF}/todolist/prioritize/task`,
      title: 'Prioritize a task',
      name: 'Prioritize'
    },
    deprioritize: {
      href: `${SERVICE_HREF}/todolist/deprioritize/task`,
      title: 'Deprioritize a task',
      name: 'Deprioritize'
    }
  },
  _templates: {
    default: {
      method: 'DELETE',
      properties: [],
      target: `${SERVICE_HREF}/tasks/91f66717-1899-46ba-b398-eaaac31b3cd4`
    },
    rename: {
      method: 'PUT',
      properties: [
        {
          name: 'description',
          readOnly: true,
          type: 'text'
        }
      ],
      target: `${SERVICE_HREF}/tasks/91f66717-1899-46ba-b398-eaaac31b3cd4/rename`
    },
    complete: {
      method: 'PUT',
      properties: [],
      target: `${SERVICE_HREF}/tasks/91f66717-1899-46ba-b398-eaaac31b3cd4/complete`
    },
    prioritize: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: `${SERVICE_HREF}/todolist/prioritize/task`
    },
    deprioritize: {
      method: 'POST',
      properties: [
        {
          name: 'id',
          readOnly: true
        }
      ],
      target: `${SERVICE_HREF}/todolist/deprioritize/task`
    }
  }
};
