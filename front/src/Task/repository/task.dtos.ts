export interface TaskDto {
  id: string;
  description: string;
  completed: boolean;
  creationTime: string;
  duration: string | null;
  _links: object;
  _templates: object;
}
