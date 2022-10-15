import { useQuery } from '@tanstack/react-query';
import { Todolist } from './todolist.dtos';

export const useTodolist = () => useQuery<Todolist>(['todolist']);
