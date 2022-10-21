import { useQueryClient } from '@tanstack/react-query';
import { useTodolist, useTodolistAction } from './repository/todolist.repository';
import { getActions } from './todolist.service';
import { Task } from '../Task';
import './todolist.scss';

export const Todolist = () => {
  const queryClient = useQueryClient();
  const { data: todolist } = useTodolist();
  const { mutate } = useTodolistAction(queryClient);

  const { prioritizeAction, deprioritizeAction } = getActions(todolist);

  return (
    <main data-testid="todolist" className="todolist">
      <h1>Welcome to the ULTIMATE Todolist! 🚀</h1>
      {todolist &&
        todolist.tasks.map((task) => (
          <div className="todolist__task" data-testid={`task-${task.id}`} key={task.id}>
            <Task {...task} />
            {prioritizeAction && (
              <button onClick={() => mutate({ action: prioritizeAction, task })}>Prioritize</button>
            )}
            {deprioritizeAction && (
              <button onClick={() => mutate({ action: deprioritizeAction, task })}>
                Deprioritize
              </button>
            )}
          </div>
        ))}
    </main>
  );
};
