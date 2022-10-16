export interface Task {
  id: string;
  description: string;
  completed: boolean;
  creationTime: Date;
  duration?: string;
}

export const taskExample = {
  id: '1a8d707e-506a-4923-a688-66cbc5674f13',
  description: 'Allé fumé du canon avec Gégé',
  completed: false,
  creationTime: new Date('2022-09-16T07:10:00'),
  duration: undefined
};
