import { TaskResource } from '../../Task/repository/task.dto';

export interface Resource {
  _links: object;
  _templates: object;
}

export interface TodolistResource extends Resource {
  tasks: Array<TaskResource>;
}
