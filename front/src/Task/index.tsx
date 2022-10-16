import { TaskResource } from './repository/task.dtos';

export const Task = ({ id, completed, creationTime, description, duration }: TaskResource) => (
  <div key={id} data-testid={`task-${id}`} className="task">
    <p>{description}</p>
    <p>{completed ? '✅' : '💪'}</p>
  </div>
);
