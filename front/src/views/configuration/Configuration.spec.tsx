import { screen } from '@testing-library/react';
import { Configuration } from './index';
import { renderWithData } from '../../utils/test-utils';

describe('Configuration view', () => {
  it('shows configuration doc', () => {
    renderWithData(<Configuration />, {
      configuration: { env_description: 'value123' }
    });

    const doc = screen.getByRole('article');
    expect(doc).toBeInTheDocument();
    expect(doc.textContent).toContain('secret manager');
  });

  it('loads configuration', async () => {
    renderWithData(<Configuration />, {
      configuration: { env_description: 'value123' }
    });
    const conf = screen.getByRole('main');
    expect(conf).toBeInTheDocument();
    expect(conf.textContent).toContain('value123');
  });
});
