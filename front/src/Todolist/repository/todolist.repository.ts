import axios from 'axios';
import { QueryClient, useMutation, useQuery } from '@tanstack/react-query';
import { Todolist, todolistExample } from '../model/todolist.model';
import { toTodolist } from './todolist.mapper';
import { Task } from '../../Task/model/task.model';
import { Action } from '../../shared/types/hateoas.types';

export const getTodolist = async () => {
  const { data: todolistResource } = await axios.get(`${process.env.SERVICE_URL}/todolist`);
  return toTodolist(todolistResource);
  // return todolistExample;
};

export const useTodolist = () => useQuery<Todolist>(['todolist'], getTodolist);

export const todolistAction = async (action: Action, task: Task): Promise<Todolist> => {
  const { data: todolistResource } = await axios.request({
    method: action.method,
    url: action.url,
    data: task
  });
  return toTodolist(todolistResource);
};

export const useTodolistAction = (queryClient: QueryClient) =>
  useMutation(({ action, task }: { action: Action; task: Task }) => todolistAction(action, task), {
    onSuccess: (todolist) => console.log(todolist)
  });
