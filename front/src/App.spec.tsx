import {render, screen} from '@testing-library/react';
import App from './App';

describe('App component', () => {
  it('displays title', async () => {
    render(<App />);

    expect(await screen.findByText(/Starter react/i)).toBeInTheDocument();

    const titleElement = screen.getByText(/Starter react/i);
    expect(titleElement).toBeInTheDocument();
  });
});
