import { isNull } from '../../shared/utils/object.utils';
import { TodolistResource } from './todolist.dtos';
import { Todolist } from '../model/todolist.model';
import { toTask } from '../../Task/infra/task.mapper';
import {
  Action,
  HTTP_METHOD,
  Links,
  Property,
  ResourceLink,
  ResourceProperty,
  ResourceTemplate,
  Templates
} from '../../shared/types/hateoas.types';

const toProperties = (properties: Array<ResourceProperty>): Array<Property> =>
  properties.map((property) => ({
    name: property.name,
    type: property.type
  }));

const toAction = (link: ResourceLink, template: ResourceTemplate): Action => ({
  method: HTTP_METHOD[template.method],
  name: link.name!,
  url: link.href,
  properties: toProperties(template.properties)
});

const checkTemplates = (templates: Templates | undefined): void => {
  if (!templates || isNull(templates)) {
    throw new TypeError('Templates (_templates) are missing in Resource.');
  }
};

const checkTemplate = (template: ResourceTemplate | undefined, name: string, key: string): void => {
  if (!template || isNull(template)) {
    throw new TypeError(`${name} (${key}) template is missing.`);
  }
};

const checkLinks = (links: Links | undefined): void => {
  if (!links || isNull(links)) {
    throw new TypeError('Links (_links) are missing in Resource.');
  }
};

const toActions = (todolistResource: TodolistResource): Array<Action> => {
  const actions: Array<Action> = [];
  checkLinks(todolistResource._links);

  Object.entries(todolistResource._links!).forEach(([key, resourceLink]) => {
    checkTemplates(todolistResource._templates);
    if (key !== 'self') {
      checkTemplate(todolistResource._templates![key], resourceLink.name!, key);
      actions.push(toAction(resourceLink, todolistResource._templates![key]));
    }
  });

  return actions;
};

export const toTodolist = (todolistResource: TodolistResource): Todolist => ({
  tasks: todolistResource.tasks.map(toTask),
  actions: toActions(todolistResource)
});
