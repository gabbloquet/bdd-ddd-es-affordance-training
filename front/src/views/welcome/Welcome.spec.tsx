import '@testing-library/jest-dom';
import { render, screen } from '@testing-library/react';
import { Welcome } from './index';

describe('Welcome view', () => {
  it('shows React Tutorial link', () => {
    render(<Welcome />);
    const linkElement = screen.getByText(/React Tutorial/i);
    expect(linkElement).toBeInTheDocument();
  });

  it('shows React Getting Started link', () => {
    render(<Welcome />);
    const linkElement = screen.getByText(/React Getting Started/i);
    expect(linkElement).toBeInTheDocument();
  });
});
