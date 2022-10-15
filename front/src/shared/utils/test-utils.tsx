import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { render } from '@testing-library/react';

export const renderWithStore = (
  component: JSX.Element,
  initializedData?: Record<string, object>
) => {
  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        retry: false,
        refetchOnMount: false
      }
    }
  });

  initializedData &&
    queryClient.setQueryData([Object.keys(initializedData)[0]], Object.values(initializedData)[0]);

  const wrapper = () => <QueryClientProvider client={queryClient}>{component}</QueryClientProvider>;

  return render(component, { wrapper });
};
