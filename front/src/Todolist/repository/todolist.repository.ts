import axios from 'axios';
import { useQuery } from '@tanstack/react-query';
import { Todolist } from '../model/todolist.model';
import { toTodolist } from './todolist.mapper';

export const getTodolist = async () => {
  const { data: todolistResource } = await axios.get(`${process.env.SERVICE_URL}/todolist`);
  return toTodolist(todolistResource);
};

export const useTodolist = () => useQuery<Todolist>(['todolist'], getTodolist);
