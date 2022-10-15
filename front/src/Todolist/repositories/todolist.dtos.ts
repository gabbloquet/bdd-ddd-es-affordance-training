import { TaskDto } from '../../Task/repository/task.dtos';

export interface Todolist {
  tasks: Array<TaskDto>;
}
