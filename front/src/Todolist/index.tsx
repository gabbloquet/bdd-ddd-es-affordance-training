import { useTodolist } from './repository/todolist.repository';
import { Task } from '../Task';
import { TODOLIST_ACTIONS, TodolistAction } from './model/todolist.model';
import './todolist.scss';
import CommandHelper from '../shared/utils/event/EventHelper';

export const Todolist = () => {
  const { data: todolist } = useTodolist();

  const prioritizeAction =
    todolist && todolist.actions && getAction(todolist.actions, TODOLIST_ACTIONS.PRIORITIZE_TASK);
  const deprioritizeAction =
    todolist &&
    todolist.actions &&
    getAction(todolist.actions, TODOLIST_ACTIONS.DEPRIORITIZE_ACTION);

  return (
    <main data-testid="todolist" className="todolist">
      <h1>Welcome to the ULTIMATE Todolist! 🚀</h1>
      {todolist &&
        todolist.tasks.map((task) => (
          <div className="todolist__task" data-testid={`task-${task.id}`} key={task.id}>
            <Task {...task} />
            {prioritizeAction && (
              <button onClick={() => CommandHelper.emit(prioritizeAction, task)}>Prioritize</button>
            )}
            {deprioritizeAction && (
              <button onClick={() => CommandHelper.emit(deprioritizeAction, task)}>
                Deprioritize
              </button>
            )}
          </div>
        ))}
    </main>
  );
};

const getAction = (
  actions: Array<TodolistAction>,
  actionToFind: TODOLIST_ACTIONS
): TodolistAction | undefined =>
  actions.find((action: TodolistAction) => action.type === actionToFind);
