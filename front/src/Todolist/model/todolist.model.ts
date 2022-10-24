import { anotherTaskCreated, Task, taskCreated } from '../../Task/model/task.model';
import { Action, HTTP_METHOD } from '../../shared/types/hateoas.types';

export interface Todolist {
  tasks: Array<Task>;
  actions: Array<Action>;
}

export const priorizationAction: Action = {
  method: HTTP_METHOD.POST,
  name: 'Prioritize',
  url: 'http://localhost:8080/todolist/prioritize/task',
  properties: [
    {
      name: 'id'
    }
  ]
};

export const depriorizationAction: Action = {
  method: HTTP_METHOD.POST,
  name: 'Deprioritize',
  url: 'http://localhost:8080/todolist/deprioritize/task',
  properties: [
    {
      name: 'id'
    }
  ]
};

export const addTaskAction: Action = {
  method: HTTP_METHOD.POST,
  name: 'Add a task',
  properties: [
    {
      name: 'description'
    }
  ],
  url: 'http://localhost:8080/tasks'
};

export const emptyTodolist: Todolist = {
  tasks: [],
  actions: [priorizationAction, depriorizationAction, addTaskAction]
};

export const todolistWithTwoTasks: Todolist = {
  tasks: [taskCreated, anotherTaskCreated],
  actions: [priorizationAction, depriorizationAction, addTaskAction]
};
