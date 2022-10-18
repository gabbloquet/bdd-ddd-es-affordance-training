import axios from 'axios';
import { useQuery } from '@tanstack/react-query';
import { Todolist } from '../model/todolist.model';
import { toTodolist } from './todolist.mapper';

export const getTodolist = async () => {
  const { data } = await axios.get(`${process.env.SERVICE_URL}/todolist`);

  return data.map(toTodolist);
};

export const useTodolist = () => useQuery<Todolist>(['todolist']);
