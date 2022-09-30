import { Welcome } from '../views/welcome';
import { Testing } from '../views/testing';
import { Configuration } from '../views/configuration';
import { Contests } from '../views/contests';

export const ROUTES = [
  {
    url: '/',
    name: 'Welcome',
    component: <Welcome />
  },
  {
    url: '/hello',
    name: 'Testing',
    component: <Testing />
  },
  {
    url: '/configuration',
    name: 'Configuration',
    component: <Configuration />
  },
  {
    url: '/contest',
    name: 'Contests',
    component: <Contests />
  }
];
