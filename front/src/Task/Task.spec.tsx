import { render, screen } from '@testing-library/react';
import { Task } from './index';
import { taskExample } from './repository/task.example';

describe('Task', () => {
  it('uses id in data-testid', () => {
    render(<Task {...taskExample} id="toto" />);

    const task = screen.getByTestId('task-toto');

    expect(task).toBeVisible();
  });
  it('shows description', () => {
    render(<Task {...taskExample} description="Practice Testing Library" />);

    const description = screen.getByText('Practice Testing Library');

    expect(description).toBeVisible();
  });
  it('shows ✅ if completed', () => {
    render(<Task {...taskExample} completed={true} />);

    const greenTick = screen.getByText('✅');

    expect(greenTick).toBeVisible();
  });
  it('shows 💪 if to do', () => {
    render(<Task {...taskExample} completed={false} />);

    const muscle = screen.getByText('💪');

    expect(muscle).toBeVisible();
  });
  it('shows the creation time with natural language', () => {
    render(<Task {...taskExample} creationTime={'2022-10-16T06:30:00'} />);

    const date = screen.getByText('(Créée le 10/16/2022 à 6:30)');

    expect(date).toBeVisible();
  });
});
