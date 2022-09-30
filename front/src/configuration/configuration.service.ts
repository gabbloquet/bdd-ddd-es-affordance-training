import axios from 'axios';
import { useQuery } from '@tanstack/react-query';

export interface Configuration {
  env_description: string;
}

export const getConfiguration = async (): Promise<Configuration> => {
  console.log('getConfiguration');
  const confVersion = Date.now().toString();
  const { data } = await axios.get(`/conf.json?_version=${confVersion}`);
  return data;
};
export const useConfiguration = () => useQuery(['configuration'], getConfiguration);
