import { ComponentStory, ComponentMeta } from '@storybook/react';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { taskCompleted, taskCreated } from '../../src/Task/task.model';
import { Todolist } from '../../src/Todolist';

const queryClient = new QueryClient({});
queryClient.setQueryData(['todolist'], {
  tasks: [taskCreated, taskCompleted]
});

export default {
  title: 'Todolist',
  component: Todolist
} as ComponentMeta<typeof Todolist>;

const Template: ComponentStory<typeof Todolist> = () => (
  <QueryClientProvider client={queryClient}>
    <Todolist />
  </QueryClientProvider>
);

export const WithCreatedTaskAndCompletedTask = Template.bind({});
