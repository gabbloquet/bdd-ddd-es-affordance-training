import { Task as TaskType } from './task.model';
import { toLiteralDateTime } from './task.service';
import './task.scss';

export const Task = ({ id, completed, creationTime, description, duration }: TaskType) => (
  <div key={id} data-testid={`task-${id}`} className="task">
    <p>{completed ? '✅' : '💪'}</p>
    <p className="description">{description}</p>
    <p className="duration">{duration}</p>
    <p>{`(Créée le ${toLiteralDateTime(creationTime)})`}</p>
  </div>
);
