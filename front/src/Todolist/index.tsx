import { useTodolist } from './repository/todolist.repository';
import { Task } from '../Task';
import './todolist.scss';

export const Todolist = () => {
  const { data: todolist } = useTodolist();

  return (
    <main data-testid="todolist" className="todolist">
      <h1>Welcome to the ULTIMATE Todolist! 🚀</h1>
      {todolist &&
        todolist.tasks.map((task) => (
          <div data-testid={`task-${task.id}`} key={task.id}>
            <Task {...task} />
            <button data-testid={`prioritize-task-${task.id}`}>Prioritize</button>
          </div>
        ))}
    </main>
  );
};
