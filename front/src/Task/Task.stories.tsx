import React from 'react';
import { ComponentStory, ComponentMeta } from '@storybook/react';

import { Task } from './index';

export default {
  title: 'Task',
  component: Task
} as ComponentMeta<typeof Task>;

const Template: ComponentStory<typeof Task> = (args) => <Task {...args} />;

export const Default = Template.bind({});
Default.args = {
  id: '1a8d707e-506a-4923-a688-66cbc5674f13',
  description: 'Allé fumé du canon avec Gégé',
  completed: false,
  creationTime: new Date('2022-09-16T07:10:00'),
  duration: '23 heure(s) et 6 minute(s)'
};

export const Completed = Template.bind({});
Completed.args = {
  id: '1a8d707e-506a-4923-a688-66cbc5674f13',
  description: 'Allé fumé du canon avec Gégé',
  completed: false,
  creationTime: new Date('2022-09-16T07:10:00'),
  duration: undefined
};
