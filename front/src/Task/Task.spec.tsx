import { render, screen } from '@testing-library/react';
import { Task } from './index';

describe('Task', () => {
  it('shows a mega title', () => {
    render(<Task />);

    const task = screen.getByText('My task');

    expect(task).toBeVisible();
  });
});
