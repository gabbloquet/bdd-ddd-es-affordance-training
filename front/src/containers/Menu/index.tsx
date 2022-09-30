import { NavLink } from 'react-router-dom';
import { ROUTES } from '../../configuration/routes';
import './menu.scss';

export const Menu = () => (
  <div className="main-menu">
    <h1 className="main-title">Starter react</h1>
    {ROUTES.map((route) => (
      <NavLink to={route.url} key={route.name} end>
        {route.name}
      </NavLink>
    ))}
  </div>
);
