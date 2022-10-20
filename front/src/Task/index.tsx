import { Task as TaskState } from './model/task.model';
import { toLiteralDateTime } from './model/task.service';
import './task.scss';

export const Task = ({ id, completed, creationTime, description, duration }: TaskState) => (
  <div className="task">
    <p>{completed ? '✅' : '💪'}</p>
    <p className="description">{description}</p>
    <p className="duration">{duration}</p>
    <p>{`(Créée le ${toLiteralDateTime(creationTime)})`}</p>
  </div>
);
