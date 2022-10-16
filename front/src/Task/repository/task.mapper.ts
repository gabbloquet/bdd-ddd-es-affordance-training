import { TaskResource } from './task.dto';
import { Task } from '../task.model';

export const toTask = (taskDto: TaskResource): Task => ({
  id: taskDto.id,
  description: taskDto.description,
  completed: taskDto.completed,
  creationTime: new Date(taskDto.creationTime),
  duration: taskDto.duration ?? undefined
});
