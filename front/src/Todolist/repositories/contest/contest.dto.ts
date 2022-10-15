export interface ContestDto {
  name: string;
  url: string;
  start_time: string;
  end_time: string;
  duration: string;
  site:
    | 'CodeForces'
    | 'CodeForces::Gym'
    | 'TopCoder'
    | 'AtCoder'
    | 'CS Academy'
    | 'CodeChef'
    | 'HackerRank'
    | 'HackerEarth'
    | 'Kick Start'
    | 'LeetCode'
    | 'Toph';
  in_24_hours: 'Yes' | 'No';
  status: 'CODING' | 'BEFORE';
}

export const contestsDtoExample = [
  {
    name: 'ProjectEuler+',
    url: 'https://hackerrank.com/contests/projecteuler',
    start_time: '2014-07-07T15:38:00.000Z',
    end_time: '2024-07-30T18:30:00.000Z',
    duration: '317616720.0',
    site: 'HackerRank',
    in_24_hours: 'No',
    status: 'CODING'
  }
];
