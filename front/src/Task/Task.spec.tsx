import { render, screen } from '@testing-library/react';
import { taskCreated } from './model/task.model';
import { Task } from './index';

describe('Task', () => {
  it('shows description', () => {
    render(<Task {...taskCreated} description="Practice Testing Library" />);

    const description = screen.getByText('Practice Testing Library');

    expect(description).toBeVisible();
  });
  it('shows ✅ if completed', () => {
    render(<Task {...taskCreated} completed={true} />);

    const greenTick = screen.getByText('✅');

    expect(greenTick).toBeVisible();
  });
  it('shows 💪 if to do', () => {
    render(<Task {...taskCreated} completed={false} />);

    const muscle = screen.getByText('💪');

    expect(muscle).toBeVisible();
  });
  it('shows the creation time with natural language', () => {
    const dateTime = new Date('2022-10-16T06:30:00');
    render(<Task {...taskCreated} creationTime={dateTime} />);

    const date = screen.getByText('(Créée le 16/10/2022 à 06:30)');

    expect(date).toBeVisible();
  });
  it('shows duration', () => {
    render(<Task {...taskCreated} duration={'2 jour(s) et 1 heure(s)'} />);

    const duration = screen.getByText('2 jour(s) et 1 heure(s)');

    expect(duration).toBeVisible();
  });
});
