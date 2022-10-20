import { Task, taskCreated } from '../../Task/model/task.model';
import { Action, HTTP_METHOD } from '../../shared/types/hateoas.types';

export enum TODOLIST_ACTIONS {
  PRIORITIZE_TASK = 'PRIORITIZE_TASK',
  DEPRIORITIZE_ACTION = 'DEPRIORITIZE_ACTION'
}

export interface TodolistAction extends Action {
  type: TODOLIST_ACTIONS;
}

export interface Todolist {
  tasks: Array<Task>;
  actions: Array<TodolistAction>;
}

export const todolistExample: Todolist = {
  tasks: [taskCreated],
  actions: []
};

export const priorizationAction: TodolistAction = {
  method: HTTP_METHOD.POST,
  type: TODOLIST_ACTIONS.PRIORITIZE_TASK,
  url: new URL('http://localhost:8080/todolist/prioritize/task'),
  properties: [
    {
      name: 'id'
    }
  ]
};

export const depriorizationAction: TodolistAction = {
  method: HTTP_METHOD.POST,
  type: TODOLIST_ACTIONS.DEPRIORITIZE_ACTION,
  url: new URL('http://localhost:8080/todolist/deprioritize/task'),
  properties: [
    {
      name: 'id'
    }
  ]
};
