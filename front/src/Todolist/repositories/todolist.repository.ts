import { useQuery } from '@tanstack/react-query';
import { TodolistResource } from './todolist.dtos';

export const useTodolist = () => useQuery<TodolistResource>(['todolist']);
