import '@testing-library/jest-dom';
import { render, screen } from '@testing-library/react';
import { Router } from './index';
import { MemoryRouter } from 'react-router-dom';
import { ROUTES } from '../../configuration/routes';

describe('Router component', () => {
  it('renders menu', () => {
    render(
      <MemoryRouter basename={'/'} initialEntries={['/']}>
        <Router />
      </MemoryRouter>
    );

    ROUTES.forEach((route) => {
      expect(screen.getByText(route.name)).toBeVisible();
    });
  });

  it('renders homepage by default', () => {
    render(
      <MemoryRouter basename={'/'} initialEntries={['/']}>
        <Router />
      </MemoryRouter>
    );

    const titleElement = screen.getByText(
      /Welcome to the application StarterHub generated for you!/i
    );
    expect(titleElement).toBeVisible();
  });
});
