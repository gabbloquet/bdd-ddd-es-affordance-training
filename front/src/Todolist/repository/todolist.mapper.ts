import { TodolistResource } from './todolist.dtos';
import { Todolist, TODOLIST_ACTIONS, TodolistAction } from '../model/todolist.model';
import { toTask } from '../../Task/repository/task.mapper';
import {
  HTTP_METHOD,
  Links,
  Property,
  ResourceProperty,
  Templates
} from '../../shared/types/hateoas.types';
import { isNull } from '../../shared/utils/object.utils';

const toProperties = (properties: Array<ResourceProperty>): Array<Property> =>
  properties.map((property) => ({
    name: property.name,
    ...(property.type && { type: property.type })
  }));

const getTaskAction = (templates: Templates, templateName: string): TodolistAction => ({
  method: templates[templateName].method === 'POST' ? HTTP_METHOD.POST : HTTP_METHOD.GET,
  type:
    templateName === 'default'
      ? TODOLIST_ACTIONS.PRIORITIZE_TASK
      : TODOLIST_ACTIONS.DEPRIORITIZE_ACTION,
  url: new URL(templates[templateName].target),
  properties: toProperties(templates[templateName].properties)
});

const checkMissingLinks = (links: Links | undefined) => {
  if (links === undefined || isNull(links)) throw new TypeError('Ressource links are missing.');

  if (links['prioritizeTask'] === undefined || isNull(links['prioritizeTask'])) {
    throw new TypeError('Prioritize task link in missing.');
  }

  if (links['deprioritizeTask'] === undefined || isNull(links['deprioritizeTask'])) {
    throw new TypeError('Deprioritize task link in missing.');
  }
};

const checkMissingTemplates = (
  templates: Templates | undefined,
  templateNames: Array<string | undefined>
) => {
  if (templates === undefined || isNull(templates)) {
    throw new TypeError('Ressource templates are missing.');
  }
  templateNames.forEach((templateName) => {
    if (
      templateName === undefined ||
      templates[templateName] === undefined ||
      isNull(templates[templateName])
    ) {
      throw new TypeError(`${templateName} template is missing.`);
    }
  });
};

const toActions = (todolistResource: TodolistResource): Array<TodolistAction> => {
  checkMissingLinks(todolistResource._links);
  const prioritizeTemplateName = todolistResource._links!['prioritizeTask'].name;
  const deprioritizeTemplateName = todolistResource._links!['deprioritizeTask'].name;

  checkMissingTemplates(todolistResource._templates, [
    prioritizeTemplateName,
    deprioritizeTemplateName
  ]);
  const prioritizeTaskAction = getTaskAction(todolistResource._templates!, prioritizeTemplateName!);
  const deprioritizeTaskAction = getTaskAction(
    todolistResource._templates!,
    deprioritizeTemplateName!
  );

  return [prioritizeTaskAction, deprioritizeTaskAction];
};

export const toTodolist = (todolistResource: TodolistResource): Todolist => ({
  tasks: todolistResource.tasks.map(toTask),
  actions: toActions(todolistResource)
});