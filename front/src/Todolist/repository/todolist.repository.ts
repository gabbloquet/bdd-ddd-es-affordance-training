import axios from 'axios';
import { QueryClient, useMutation, useQuery } from '@tanstack/react-query';
import { Todolist, todolistExample } from '../model/todolist.model';
import { toTodolist } from './todolist.mapper';
import { Task } from '../../Task/model/task.model';
import { TodolistResource } from './todolist.dtos';

export const getTodolist = async () => {
  // const { data: todolistResource } = await axios.get(`${process.env.SERVICE_URL}/todolist`);
  // return toTodolist(todolistResource);
  return todolistExample;
};

export const useTodolist = () => useQuery<Todolist>(['todolist'], getTodolist);

export const prioritizeTask = async (task: Task) => {
  const { data: todolistResource } = await axios.post(`${process.env.SERVICE_URL}/todolist`);
  return toTodolist(todolistResource);
};

export const usePrioritizeTask = (queryClient: QueryClient) =>
  useMutation(
    (task: Task) => {
      console.log('popop bien de trop ', task);
      return prioritizeTask(task);
    },
    {
      onSuccess: (todolist: Todolist) => {
        queryClient.setQueryData(['todolist'], () => todolist);
      }
    }
  );
