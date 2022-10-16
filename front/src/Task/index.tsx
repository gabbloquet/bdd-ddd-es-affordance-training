import { Task as TaskType } from './task.model';
import { toLiteralDateTime } from './task.service';

export const Task = ({ id, completed, creationTime, description, duration }: TaskType) => (
  <div key={id} data-testid={`task-${id}`} className="task">
    <p>{description}</p>
    <p>{completed ? '✅' : '💪'}</p>
    <p>{`(Créée le ${toLiteralDateTime(creationTime)})`}</p>
  </div>
);
