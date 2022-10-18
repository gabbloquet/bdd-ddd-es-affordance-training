import { TodolistResource } from './todolist.dtos';
import { Todolist, TODOLIST_ACTIONS, TodolistAction } from '../model/todolist.model';
import { toTask } from '../../Task/repository/task.mapper';
import { HTTP_METHOD, ResourceTemplate } from '../../shared/types/hateoas.types';

const getPrioritizeTaskAction = (template: ResourceTemplate): TodolistAction => ({
  method: template.method === 'POST' ? HTTP_METHOD.POST : HTTP_METHOD.GET,
  type: TODOLIST_ACTIONS.PRIORITIZE_TASK,
  url: new URL(template.target),
  properties: template.properties
});

const toActions = (todolistResource: TodolistResource): Array<TodolistAction> => {
  if (
    todolistResource._links === undefined ||
    todolistResource._links.get('prioritizeTask') === undefined
  ) {
    throw new Error('Prioritize task link in not defined.');
  }
  const templateName = todolistResource._links.get('prioritizeTask')?.name;

  if (
    templateName === undefined ||
    todolistResource._templates === undefined ||
    todolistResource._templates.get(templateName) === undefined
  ) {
    throw new Error('Prioritize task template in not defined.');
  }

  const prioritizeTaskAction = getPrioritizeTaskAction(
    todolistResource._templates.get(templateName)!
  );

  return [prioritizeTaskAction];
};

export const toTodolist = (todolistResource: TodolistResource): Todolist => {
  return {
    tasks: todolistResource.tasks.map(toTask),
    actions: toActions(todolistResource)
  };
};
