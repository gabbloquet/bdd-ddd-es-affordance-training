import { useConfiguration } from '../../configuration/configuration.service';
import './configuration.scss';

export const Configuration = () => {
  const { data: configuration } = useConfiguration();

  return (
    <div className="Configuration">
      <h1>Documentation</h1>

      <p role="article">
        When deployed remotely, the configuration is provided from Vault. Locally, it's provided by
        the file `public/conf.json`.
      </p>

      <h2>Configuration</h2>

      <p>The current configuration is shown bellow.</p>

      <pre role="main">{JSON.stringify(configuration, null, 2)}</pre>

      <h2>Suggestion</h2>

      <p>For example, the configuration may include:</p>

      <ul>
        <li>The API endpoint URL for each env</li>
        <li>Additional headers provided by nginx (see docker/nginx.conf and Dockerfile)</li>
      </ul>
    </div>
  );
};
