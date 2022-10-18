import { TodolistResource } from './todolist.dtos';
import { Todolist } from '../model/todolist.model';

export const toTodolist = (todolistResource: TodolistResource): Todolist => {
  return {
    tasks: []
  };
};
