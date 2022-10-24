import { useQueryClient } from '@tanstack/react-query';
import { useAddTaskAction, useTodolist, useTodolistAction } from './repository/todolist.repository';
import { Action } from '../shared/types/hateoas.types';
import { Todolist as TodolistState } from './model/todolist.model';
import { Task } from '../Task';
import './todolist.scss';

const getAddTaskAction = (todolist: TodolistState | undefined) =>
  todolist &&
  todolist.actions &&
  todolist.actions.find((action: Action) => action.name === 'Add a task');

export const Todolist = () => {
  const queryClient = useQueryClient();
  const { data: todolist } = useTodolist();
  const { mutate } = useTodolistAction(queryClient);
  const { mutate: addTaskMutate } = useAddTaskAction(queryClient);

  const addTaskAction = getAddTaskAction(todolist);

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    event.key === 'Enter' &&
      addTaskAction &&
      addTaskMutate({
        action: addTaskAction,
        task: { description: (event.target as HTMLTextAreaElement).value }
      });
  };

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

      {addTaskAction && (
        <div className="todolist__task" key="add-task">
          <input
            name={addTaskAction.name}
            placeholder={addTaskAction.name}
            onKeyDown={handleKeyDown}
          />
        </div>
      )}
    </main>
  );
};
