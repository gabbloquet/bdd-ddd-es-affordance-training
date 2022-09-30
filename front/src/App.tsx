import { BrowserRouter } from 'react-router-dom';
import { Router } from './containers/Router';
import { useConfiguration } from './configuration/configuration.service';
import Loader from './views/components/Loader';

const App = () => {
  const { isFetched, isLoading } = useConfiguration();

  return (
    <div className="App">
      {isLoading && (
        <>
          <Loader data-testid="configuration-loader" />
          <span data-testid="configuration-loader-text">Loading configuration…</span>
        </>
      )}
      {isFetched && (
        <BrowserRouter>
          <Router />
        </BrowserRouter>
      )}
    </div>
  );
};

export default App;
