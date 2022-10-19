import { Task as TaskState } from './model/task.model';
import { toLiteralDateTime } from './model/task.service';
import './task.scss';

export const Task = ({ id, completed, creationTime, description, duration }: TaskState) => (
  <tr className="task">
    <td>{completed ? '✅' : '💪'}</td>
    <td className="description">{description}</td>
    <td className="duration">{duration}</td>
    <td>{`(Créée le ${toLiteralDateTime(creationTime)})`}</td>
  </tr>
);
