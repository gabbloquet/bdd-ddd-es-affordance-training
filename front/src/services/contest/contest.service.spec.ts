import * as nock from 'nock';
import axios from 'axios';
import { getContests } from './contest.service';
import { contestsDtoExample } from './contest.dto';

axios.defaults.adapter = require('axios/lib/adapters/http');

describe('Contests service', () => {
  it('gets contests Kontests API', async () => {
    nock('https://kontests.net').get('/api/v1/all').reply(200, contestsDtoExample);

    const data = await getContests();

    const expectedMappedData = [
      {
        title: 'ProjectEuler+',
        url: 'https://hackerrank.com/contests/projecteuler'
      }
    ];
    expect(data).toStrictEqual(expectedMappedData);
  });
});
