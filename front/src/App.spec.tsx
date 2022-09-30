import '@testing-library/jest-dom';
import { screen } from '@testing-library/react';
import { renderWithData } from './utils/test-utils';
import App from './App';

describe('App component', () => {
  const configuration = { env_description: 'value123' };

  it('displays title', async () => {
    renderWithData(<App />, {
      configuration
    });

    expect(await screen.findByText(/Starter react/i)).toBeInTheDocument();

    const titleElement = screen.getByText(/Starter react/i);
    expect(titleElement).toBeInTheDocument();
  });
});
