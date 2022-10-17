import { Task, taskCreated } from '../../Task/model/task.model';

export interface Todolist {
  tasks: Array<Task>;
}

export const todolistExample: Todolist = {
  tasks: [taskCreated]
};
