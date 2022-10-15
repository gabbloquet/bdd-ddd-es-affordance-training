import { render, screen } from '@testing-library/react';
import App from './App';
import { renderWithStore } from './shared/utils/test-utils';

describe('App component', () => {
  it('displays todolist', async () => {
    renderWithStore(<App />);

    const todolist = screen.getByTestId('todolist');

    expect(todolist).toBeVisible();
  });
});
