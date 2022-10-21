import { TodolistResource } from './todolist.dtos';
import { Todolist } from '../model/todolist.model';
import { toTask } from '../../Task/repository/task.mapper';
import {
  Action,
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

const getTaskAction = (templates: Templates, templateName: string): Action => ({
  method: templates[templateName].method === 'POST' ? HTTP_METHOD.POST : HTTP_METHOD.GET,
  name: 'TODO',
  url: templates[templateName].target,
  properties: toProperties(templates[templateName].properties)
});

const checkMissingLinks = (links: Links | undefined) => {
  if (links === undefined || isNull(links)) throw new TypeError('Ressource links are missing.');

  if (links['default'] === undefined || isNull(links['default'])) {
    throw new TypeError('Prioritize task link in missing.');
  }

  if (links['deprioritize'] === undefined || isNull(links['deprioritize'])) {
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

const buildActions = (
  templates: Templates | undefined,
  templateNames: Array<string | undefined>
): Array<Action> => {
  checkMissingTemplates(templates, templateNames);
  return templateNames.map((templateName) => getTaskAction(templates!, templateName!));
};

const toActions = (todolistResource: TodolistResource): Array<Action> => {
  checkMissingLinks(todolistResource._links);

  // TODO: rendre ça automatique, réutilisable
  const prioritizeTemplateName = 'default';
  const deprioritizeTemplateName = 'deprioritize';

  return buildActions(todolistResource._templates, [
    prioritizeTemplateName,
    deprioritizeTemplateName
  ]);
};

export const toTodolist = (todolistResource: TodolistResource): Todolist => ({
  tasks: todolistResource.tasks.map(toTask),
  actions: toActions(todolistResource)
});
