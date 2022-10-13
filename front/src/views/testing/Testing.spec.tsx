import { fireEvent, render, screen } from '@testing-library/react';
import { Testing } from './index';

describe('Configuration view', () => {
  it('shows your name is sentence', () => {
    render(<Testing />);

    const inputSentenceElement = screen.getByText(/Your name is/i);

    expect(inputSentenceElement).toBeInTheDocument();
  });

  it('shows typed username', () => {
    render(<Testing />);

    const input = screen.getByRole('textbox') as HTMLInputElement;
    expect(input).toBeInTheDocument();

    fireEvent.change(input, { target: { value: 'Nicolas' } });
    expect(input.value).toBe('Nicolas');

    const output = screen.getByTestId('hello-user');
    expect(output).toBeInTheDocument();
    expect(output.textContent).toContain('Hello dear Nicolas');
  });
});
