import axios from 'axios';
import { useQuery } from '@tanstack/react-query';
import { ContestDto } from './contest.dto';

export interface Contest {
  title: string;
  url: string;
}

export const getContests = async (): Promise<Array<Contest>> => {
  const { data } = await axios.get(`https://kontests.net/api/v1/all`);

  return data.map((contestDto: ContestDto) => ({
    title: contestDto.name,
    url: contestDto.url
  }));
};

export const useContests = () => useQuery(['contests'], getContests);
