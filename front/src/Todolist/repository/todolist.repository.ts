import axios from 'axios';
import { QueryClient, useMutation, useQuery } from '@tanstack/react-query';
import { Todolist, TodolistAction, todolistExample } from '../model/todolist.model';
import { toTodolist } from './todolist.mapper';
import { Task } from '../../Task/model/task.model';
import { TodolistResource } from './todolist.dtos';

export const getTodolist = async () => {
  // const { data: todolistResource } = await axios.get(`${process.env.SERVICE_URL}/todolist`);
  // return toTodolist(todolistResource);
  return todolistExample;
};

export const useTodolist = () => useQuery<Todolist>(['todolist'], getTodolist);

export const todolistAction = async (action: TodolistAction, task: Task) => {
  console.log('request : ', {
    method: action.method,
    url: action.url,
    data: task
  });
  const { data: todolistResource } = await axios.request({
    method: action.method,
    url: action.url,
    data: task
  });
  return toTodolist(todolistResource);
};

export const useTodolistAction = (queryClient: QueryClient) =>
  useMutation(
    ({ action, task }: { action: TodolistAction; task: Task }) => todolistAction(action, task),
    {
      onSuccess: (todolist) => console.log(todolist)
    }
  );
