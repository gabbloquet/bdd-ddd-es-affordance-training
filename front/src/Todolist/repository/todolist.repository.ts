import { useQuery } from '@tanstack/react-query';
import { Todolist } from '../model/todolist.model';

export const useTodolist = () => useQuery<Todolist>(['todolist']);
