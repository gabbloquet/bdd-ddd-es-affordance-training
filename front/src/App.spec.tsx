import {render, screen} from '@testing-library/react';
import App from './App';

describe('App component', () => {
  it('displays todolist', async () => {
    render(<App />);

    const titleElement = screen.getByText(/Welcome in the ultimate Todolist ! 🚀/i);
    expect(titleElement).toBeInTheDocument();
  });
});
