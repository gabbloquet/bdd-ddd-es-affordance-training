import { Route, Routes } from 'react-router-dom';
import { Menu } from '../Menu';
import { ROUTES } from '../../configuration/routes';
import './router.scss';

export const Router = () => (
  <div className="router">
    <Menu />
    <div className="main-container">
      <Routes>
        {ROUTES.map((route) => (
          <Route path={route.url} element={route.component} key={route.name} />
        ))}
      </Routes>
    </div>
  </div>
);
