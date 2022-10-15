import { render, screen } from '@testing-library/react';
import { Task } from './index';
import { taskExample } from './repository/task.examples';

describe('Task', () => {
  it('uses id in data-testid', () => {
    render(<Task {...taskExample} id="toto" />);

    const task = screen.getByTestId('task-toto');

    expect(task).toBeVisible();
  });
});
