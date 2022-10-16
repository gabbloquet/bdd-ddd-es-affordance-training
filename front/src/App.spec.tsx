import { screen } from '@testing-library/react';
import { renderWithStore } from './shared/utils/test-utils';
import App from './App';

describe('App component', () => {
  it('displays todolist', async () => {
    renderWithStore(<App />);

    const todolist = screen.getByTestId('todolist');

    expect(todolist).toBeVisible();
  });
});
