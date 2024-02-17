import axios from 'axios';
import { QueryClient, useMutation, useQuery } from '@tanstack/react-query';
import { Todolist } from '../model/todolist.model';
import { toTodolist } from '../infra/todolist.mapper';
import { Task } from '../../Task/model/task.model';
import { Action } from '../../shared/types/hateoas.types';

interface TaskToCreate {
  description: string;
}

export const getTodolist = async () => {
  const { data: todolistResource } = await axios.get(`${process.env.SERVICE_URL}/todolist`, {
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/prs.hal-forms+json'
    }
  });
  return toTodolist(todolistResource);
};

export const useTodolist = () => useQuery<Todolist>(['todolist'], getTodolist);

export const todolistAction = async (action: Action, task: Task): Promise<Todolist> => {
  const { data: todolistResource } = await axios.request({
    method: action.method,
    url: action.url,
    data: { id: task.id }
  });
  return toTodolist(todolistResource);
};

export const useTodolistAction = (queryClient: QueryClient) =>
  useMutation(({ action, task }: { action: Action; task: Task }) => todolistAction(action, task), {
    onSuccess: (todolist) => queryClient.setQueryData(['todolist'], () => todolist)
  });

export const addTaskAction = async (action: Action, task: TaskToCreate): Promise<Todolist> => {
  const { data: todolistResource } = await axios.request({
    method: action.method,
    url: action.url,
    data: task
  });
  return toTodolist(todolistResource);
};

export const useAddTaskAction = (queryClient: QueryClient) =>
  useMutation(
    ({ action, task }: { action: Action; task: TaskToCreate }) => addTaskAction(action, task),
    {
      onSuccess: (todolist) => queryClient.setQueryData(['todolist'], () => todolist)
    }
  );
