import { Task, taskCreated } from '../../Task/model/task.model';
import { Action } from '../../shared/types/hateoas.types';

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
