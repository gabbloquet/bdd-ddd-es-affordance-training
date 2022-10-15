import { render, screen } from '@testing-library/react';
import { Todolist } from './index';

describe('Todolist', () => {
  it('shows a mega title', () => {
    render(<Todolist />);

    const title = screen.getByRole('heading', {level: 1});

    expect(title).toHaveTextContent('Welcome to the ULTIMATE Todolist! 🚀');
  });
});
