import { Resource } from '../../Todolist/repositories/todolist.dtos';

export interface TaskResource extends Resource {
  id: string;
  description: string;
  completed: boolean;
  creationTime: string;
  duration: string | null;
}
