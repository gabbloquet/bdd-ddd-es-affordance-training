import { Task as TaskState } from './task.model';
import { toLiteralDateTime } from './task.service';
import './task.scss';

export const Task = ({ id, completed, creationTime, description, duration }: TaskState) => (
  <tr data-testid={`task-${id}`} className="task">
    <td>{completed ? '✅' : '💪'}</td>
    <td className="description">{description}</td>
    <td className="duration">{duration}</td>
    <td>{`(Créée le ${toLiteralDateTime(creationTime)})`}</td>
  </tr>
);
