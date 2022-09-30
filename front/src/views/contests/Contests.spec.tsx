import '@testing-library/jest-dom';
import nock from 'nock';
import axios from 'axios';
import { renderWithData } from '../../utils/test-utils';
import { Contests } from './index';
import { Contest } from '../../services/contest/contest.service';
import { screen } from '@testing-library/react';
import { contestsDtoExample } from '../../services/contest/contest.dto';

axios.defaults.adapter = require('axios/lib/adapters/http');

describe('Contests view', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('displays fetched contests', () => {
    const contests: Array<Contest> = [
      {
        title: 'TDD Contest',
        url: 'tddcontest.io'
      },
      {
        title: 'BDD Contest',
        url: 'bddcontest.io/amazing'
      }
    ];
    renderWithData(<Contests />, {
      contests
    });

    contests.forEach((contest) => {
      const testId = 'contest-name-' + contest.title;
      expect(screen.getByTestId(testId)).toHaveTextContent(`${contest.title} : `);
    });
  });

  it('displays loader when fetching', () => {
    nock('https://kontests.net').get('/api/v1/all').delay(2000).reply(200, contestsDtoExample);

    renderWithData(<Contests />);

    expect(screen.getByTestId('contests-loader')).toBeVisible();
  });
});
