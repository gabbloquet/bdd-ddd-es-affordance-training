import { useQueryClient } from '@tanstack/react-query';
import { useTodolist, useTodolistAction } from './repository/todolist.repository';
import { Action } from '../shared/types/hateoas.types';
import { Todolist as TodolistState } from './model/todolist.model';
import { Task } from '../Task';
import './todolist.scss';

const addTaskActionIsAvailable = (todolist: TodolistState | undefined) =>
  todolist &&
  todolist.actions &&
  todolist.actions.some((action: Action) => action.name === 'Add a task');

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

      {addTaskActionIsAvailable(todolist) && (
        <div className="todolist__task" key="add-task">
          <input name="Add a task" placeholder="Add a task" />
        </div>
      )}
    </main>
  );
};
