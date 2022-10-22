import { useQueryClient } from '@tanstack/react-query';
import { useTodolist, useTodolistAction } from './repository/todolist.repository';
import { Task } from '../Task';
import './todolist.scss';

export const Todolist = () => {
  const queryClient = useQueryClient();
  const { data: todolist } = useTodolist();
  const { mutate } = useTodolistAction(queryClient);

  return (
    <main data-testid="todolist" className="todolist">
      <h1>Welcome to the ULTIMATE Todolist! 🚀</h1>

      {todolist &&
        todolist.tasks.map((task) => (
          <div className="todolist__task" data-testid={`task-${task.id}`} key={task.id}>
            <Task {...task} />
            {todolist.actions.map((action) => (
              <button key={action.name} onClick={() => mutate({ action: action, task })}>
                {action.name}
              </button>
            ))}
          </div>
        ))}
    </main>
  );
};
