import { toTask } from './task.mapper';
import { TaskResource } from './task.dto';
import { Task } from '../model/task.model';
import { taskDtoExample } from './task.dto';

describe('Task mapper', () => {
  it('returns id, description, date, completed and duration', () => {
    // Given
    const taskResource: TaskResource = {
      id: '1a8d707e-506a-4923-a688-66cbc5674f13',
      description: 'Allé fumé du canon avec Gégé',
      completed: false,
      creationTime: '2022-11-15T09:04:17.725932205',
      duration: '23 heure(s) et 6 minute(s)',
      _links: {},
      _templates: {}
    };

    // When
    const task = toTask(taskResource);

    // Then
    const expectedTask: Task = {
      id: '1a8d707e-506a-4923-a688-66cbc5674f13',
      description: 'Allé fumé du canon avec Gégé',
      completed: false,
      creationTime: new Date(2022, 10, 15, 9, 4, 17, 725),
      duration: '23 heure(s) et 6 minute(s)'
    };
    expect(task).toStrictEqual(expectedTask);
  });

  it('returns undefined duration', () => {
    // Given
    const taskResource: TaskResource = {
      ...taskDtoExample,
      duration: null
    };

    // When
    const task = toTask(taskResource);

    // Then
    expect(task.duration).toBe(undefined);
  });
});
