import { screen } from '@testing-library/react';
import { renderWithStore } from '../shared/utils/test-utils';
import { Todolist } from './index';
import { taskCompleted, taskCreated } from '../Task/model/task.model';

describe('Todolist', () => {
  it('shows a welcome message', () => {
    renderWithStore(<Todolist />);

    const title = screen.getByRole('heading', { level: 1 });

    expect(title).toHaveTextContent('Welcome to the ULTIMATE Todolist! 🚀');
  });
  it('displays nothing if there is no task', () => {
    const emptyTodolist = { tasks: [] };
    renderWithStore(<Todolist />, { todolist: emptyTodolist });

    const tasks = screen.queryAllByTestId(/task-/);

    expect(tasks).toHaveLength(0);
  });
  it('displays tasks', () => {
    const todolistWithTwoTasks = {
      tasks: [taskCreated, taskCompleted]
    };
    renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

    const tasks = screen.queryAllByTestId(/task-/);

    expect(tasks).toHaveLength(2);
  });
});
