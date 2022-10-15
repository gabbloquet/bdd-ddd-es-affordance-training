import { screen } from '@testing-library/react';
import { renderWithStore } from '../shared/utils/test-utils';
import { emptyTodolist, todolistWithATask } from './repositories/todolist.examples';
import { Todolist } from './index';

describe('Todolist', () => {
  it('shows a welcome message', () => {
    renderWithStore(<Todolist />);

    const title = screen.getByRole('heading', { level: 1 });

    expect(title).toHaveTextContent('Welcome to the ULTIMATE Todolist! 🚀');
  });
  it('displays nothing if there is no task', () => {
    renderWithStore(<Todolist />, { todolist: emptyTodolist });

    const tasks = screen.queryAllByTestId(/task-/);

    expect(tasks).toHaveLength(0);
  });
  it('displays a task', () => {
    renderWithStore(<Todolist />, { todolist: todolistWithATask });

    const tasks = screen.queryAllByTestId(/task-/);

    expect(tasks).toHaveLength(1);
  });
});
