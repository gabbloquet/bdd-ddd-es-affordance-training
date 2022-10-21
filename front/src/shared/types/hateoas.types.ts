export interface ResourceLink {
  href: string;
  title: string;
  name?: string;
}

export interface ResourceTemplate {
  method: 'GET' | 'POST' | 'PUT' | 'DELETE';
  properties: Array<ResourceProperty>;
  target: string;
}

export type Links = {
  [key: string]: ResourceLink;
};

export type Templates = {
  [key: string]: ResourceTemplate;
};

export interface Resource {
  _links?: Links;
  _templates?: Templates;
}

export interface ResourceProperty {
  name: string;
  readOnly?: boolean;
  type?: string;
}

export interface Property {
  name: string;
  type?: string;
}

export enum HTTP_METHOD {
  GET = 'GET',
  POST = 'POST',
  PUT = 'PUT',
  DELETE = 'DELETE'
}

export interface Action {
  method: HTTP_METHOD;
  type: string;
  url: string;
  properties: Array<Property>;
}
