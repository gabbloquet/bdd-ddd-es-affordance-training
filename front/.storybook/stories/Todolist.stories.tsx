import { ComponentStory, ComponentMeta } from '@storybook/react';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { taskCompleted, taskCreated } from '../../src/Task/model/task.model';
import {
  addTaskAction,
  depriorizationAction,
  priorizationAction
} from '../../src/Todolist/model/todolist.model';
import { Todolist } from '../../src/Todolist';

export default {
  title: 'Todolist',
  component: Todolist
} as ComponentMeta<typeof Todolist>;

const queryClient = new QueryClient({});
queryClient.setQueryData(['todolist'], {
  tasks: [taskCreated, taskCompleted],
  actions: [priorizationAction, depriorizationAction, addTaskAction]
});
const Template: ComponentStory<typeof Todolist> = () => (
  <QueryClientProvider client={queryClient}>
    <Todolist />
  </QueryClientProvider>
);
export const WithCreatedTaskAndCompletedTask = Template.bind({});

const queryClientActions = new QueryClient({});
queryClientActions.setQueryData(['todolist'], {
  tasks: [],
  actions: [priorizationAction, depriorizationAction, addTaskAction]
});
const TemplateWithoutTask: ComponentStory<typeof Todolist> = () => (
  <QueryClientProvider client={queryClientActions}>
    <Todolist />
  </QueryClientProvider>
);
export const WithoutTask = TemplateWithoutTask.bind({});
