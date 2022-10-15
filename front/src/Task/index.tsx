import { TaskResource } from './repository/task.dtos';

export const Task = ({ id, completed, creationTime, description, duration }: TaskResource) => (
  <div data-testid={`task-${id}`} className="task">
    <p>My task</p>
  </div>
);
