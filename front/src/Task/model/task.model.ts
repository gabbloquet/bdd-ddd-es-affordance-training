export interface Task {
  id: string;
  description: string;
  completed: boolean;
  creationTime: Date;
  duration?: string;
}

export const taskCreated: Task = {
  id: '1a8d707e-506a-4923-a688-66cbc5674f13',
  description: 'Allé fumé du canon avec Gégé',
  completed: false,
  creationTime: new Date('2022-09-16T07:10:00'),
  duration: undefined
};

export const taskCompleted: Task = {
  id: '1a8d707e-506a-4923-a688-66cbc5674f13',
  description: "Allé d'égosillé du rouge chez les bolchéviques",
  completed: true,
  creationTime: new Date('2022-01-01T06:30:00'),
  duration: '2 jour(s) et 22 heure(s)'
};
