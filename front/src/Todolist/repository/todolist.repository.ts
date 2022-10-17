import { useQuery } from '@tanstack/react-query';
import { Todolist } from '../todolist.model';

export const useTodolist = () => useQuery<Todolist>(['todolist']);
