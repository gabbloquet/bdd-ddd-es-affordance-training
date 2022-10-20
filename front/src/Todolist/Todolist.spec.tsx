import { screen } from '@testing-library/react';
import { renderWithStore } from '../shared/utils/test-utils';
import { Todolist } from './index';
import { taskCompleted, taskCreated } from '../Task/model/task.model';
import { priorizationAction } from './model/todolist.model';

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
    // Given
    const todolistWithTwoTasks = {
      tasks: [taskCreated, taskCompleted]
    };

    // When
    renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

    // Then
    const tasks = screen.queryAllByTestId(/^task-/);
    expect(tasks).toHaveLength(2);
  });
  it('allows to prioritize tasks if available', () => {
    // Given
    const todolistWithTwoTasks = {
      tasks: [taskCreated, taskCompleted],
      actions: [priorizationAction]
    };

    // When
    renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

    // Then
    const buttons = screen.getAllByRole('button', { name: 'Prioritize' });
    expect(buttons).toHaveLength(2);
  });
  it('doesnt allow to prioritize tasks if not available', () => {
    // Given
    const todolistWithTwoTasks = {
      tasks: [taskCreated, taskCompleted],
      actions: []
    };

    // When
    renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

    // Then
    const buttons = screen.queryAllByRole('button', { name: 'Prioritize' });
    expect(buttons).toHaveLength(0);
  });
});
