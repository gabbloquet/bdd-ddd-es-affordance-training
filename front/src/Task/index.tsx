import { TaskResource } from './repository/task.dto';

export const Task = ({ id, completed, creationTime, description, duration }: TaskResource) => (
  <div key={id} data-testid={`task-${id}`} className="task">
    <p>{description}</p>
    <p>{completed ? '✅' : '💪'}</p>
    <p>{`(Depuis le ${new Date(creationTime).toLocaleDateString()} à ${new Date(
      creationTime
    ).toLocaleTimeString()})`}</p>
  </div>
);
