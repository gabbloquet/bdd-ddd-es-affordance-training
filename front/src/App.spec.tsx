import {render, screen} from '@testing-library/react';
import App from './App';

describe('App component', () => {
  it('displays todolist', async () => {
    render(<App />);

    const todolist = screen.getByTestId('todolist');

    expect(todolist).toBeVisible();
  });
});
