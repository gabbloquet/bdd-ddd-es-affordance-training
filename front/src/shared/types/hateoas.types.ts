export interface ResourceLink {
  href: string;
  title: string;
  name?: string;
}

export interface ResourceTemplate {
  method: 'GET' | 'POST' | 'PUT';
  properties: Array<ResourceProperty>;
  target: string;
}

export interface Resource {
  _links?: Map<string, ResourceLink>;
  _templates?: Map<string, ResourceTemplate>;
}

interface ResourceProperty {
  name: string;
  readOnly: boolean;
}

interface Property {
  name: string;
}

export enum HTTP_METHOD {
  GET = 'GET',
  POST = 'POST',
  PUT = 'PUT'
}

export interface Action {
  method: HTTP_METHOD;
  type: string;
  url: URL;
  properties: Array<Property>;
}
