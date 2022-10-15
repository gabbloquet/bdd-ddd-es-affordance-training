import { render, screen } from '@testing-library/react';
import { Welcome } from './index';

describe('Welcome view', () => {
  it('shows a mega title', () => {
    render(<Welcome />);

    const title = screen.getByRole('heading', {level: 1});

    expect(title).toHaveTextContent('Welcome to the ULTIMATE Todolist! 🚀');
  });
});
