import { useTodolist } from './repositories/todolist.repository';
import { Task } from '../Task';
import './todolist.scss';

export const Todolist = () => {
  const { data: todolist } = useTodolist();

  return (
    <div data-testid="todolist" className="todolist">
      <h1>Welcome to the ULTIMATE Todolist! 🚀</h1>
      {todolist && todolist.tasks.map((task) => <Task {...task} />)}
    </div>
  );
};
