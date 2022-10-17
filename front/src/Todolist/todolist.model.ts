import { Task, taskCreated } from '../Task/task.model';

export interface Todolist {
  tasks: Array<Task>;
}

export const todolistExample: Todolist = {
  tasks: [taskCreated]
};
