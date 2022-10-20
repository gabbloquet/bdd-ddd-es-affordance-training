import { useTodolist } from './repository/todolist.repository';
import { Task } from '../Task';
import { TODOLIST_ACTIONS, TodolistAction } from './model/todolist.model';
import './todolist.scss';

export const Todolist = () => {
  const { data: todolist } = useTodolist();

  return (
    <main data-testid="todolist" className="todolist">
      <h1>Welcome to the ULTIMATE Todolist! 🚀</h1>
      {todolist &&
        todolist.tasks.map((task) => (
          <div className="todolist__task" data-testid={`task-${task.id}`} key={task.id}>
            <Task {...task} />
            {todolist.actions && hasAction(todolist.actions, TODOLIST_ACTIONS.PRIORITIZE_TASK) && (
              <button>Prioritize</button>
            )}
            {todolist.actions &&
              hasAction(todolist.actions, TODOLIST_ACTIONS.DEPRIORITIZE_ACTION) && (
                <button>Deprioritize</button>
              )}
          </div>
        ))}
    </main>
  );
};

const hasAction = (actions: Array<TodolistAction>, actionToFind: TODOLIST_ACTIONS): boolean =>
  actions.some((action: TodolistAction) => action.type === actionToFind);
