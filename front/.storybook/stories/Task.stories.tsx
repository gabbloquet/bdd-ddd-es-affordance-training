import { ComponentStory, ComponentMeta } from '@storybook/react';

import { Task } from '../../src/Task';

export default {
  title: 'Task',
  component: Task,
  parameters: {
    controls: {
      matchers: {
        date: /creationTime$/
      }
    }
  }
} as ComponentMeta<typeof Task>;

const Template: ComponentStory<typeof Task> = (args) => <Task {...args} />;

export const JustCreated = Template.bind({});
JustCreated.args = {
  id: '1a8d707e-506a-4923-a688-66cbc5674f13',
  description: 'Allé fumé du canon avec Gégé',
  completed: false,
  creationTime: new Date('2022-09-16T07:10:00'),
  duration: undefined
};

export const ToDo = Template.bind({});
ToDo.args = {
  id: '1a8d606e-506a-4923-a688-66cbc5674f13',
  description: 'Allé fumé du canon avec Gégé',
  completed: false,
  creationTime: new Date('2022-09-16T07:10:00'),
  duration: '1 heure(s) et 10 minute(s)'
};

export const Completed = Template.bind({});
Completed.args = {
  id: '1a8d808e-506a-4923-a688-66cbc5674f13',
  description: 'Allé fumé du canon avec Gégé',
  completed: true,
  creationTime: new Date('2022-09-16T07:10:00'),
  duration: '23 heure(s) et 6 minute(s)'
};
