import axios from 'axios';
import { getConfiguration } from './configuration.service';

describe('Configuration service', () => {
  jest.useFakeTimers().setSystemTime(new Date('2022-01-01'));
  const getSpy = jest.spyOn(axios, 'get');

  it('calls configuration file', () => {
    getConfiguration();

    expect(getSpy).toHaveBeenCalledTimes(1);
    expect(getSpy).toHaveBeenCalledWith('/conf.json?_version=1640995200000');
  });

  it('returns data', () => {
    getSpy.mockResolvedValueOnce({ data: { AMAZING_KEY: '123456' } });

    getConfiguration().then((receivedConfiguration) => {
      expect(receivedConfiguration).toEqual({ AMAZING_KEY: '123456' });
    });
  });
});
