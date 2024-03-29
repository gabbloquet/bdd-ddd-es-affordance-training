import axios from 'axios';
import { fireEvent, screen } from '@testing-library/react';
import { renderWithStore } from '../shared/utils/test-utils';
import { taskCompleted, taskCreated } from '../Task/model/task.model';
import { addTaskAction, depriorizationAction, priorizationAction } from './model/todolist.model';
import * as todolistRepository from './repository/todolist.repository';
import { Todolist } from './index';

axios.defaults.adapter = require('axios/lib/adapters/http');

describe('Todolist', () => {
  const todolistActionSpy = jest.fn();
  const addTaskActionSpy = jest.fn();

  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('shows a welcome message', () => {
    renderWithStore(<Todolist />);

    const title = screen.getByRole('heading', { level: 1 });

    expect(title).toHaveTextContent('Welcome to the ULTIMATE Todolist! 🚀');
  });
  it('displays nothing if there is no task', () => {
    const emptyTodolist = { tasks: [] };
    renderWithStore(<Todolist />, { todolist: emptyTodolist });

    const tasks = screen.queryAllByTestId(/task-/);

    expect(tasks).toHaveLength(0);
  });
  it('displays tasks', () => {
    // Given
    const todolistWithTwoTasks = {
      tasks: [taskCreated, taskCompleted],
      actions: []
    };

    // When
    renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

    // Then
    const tasks = screen.queryAllByTestId(/^task-/);
    expect(tasks).toHaveLength(2);
  });

  describe('Task prioritization', () => {
    it('allows to prioritize tasks if available', () => {
      // Given
      const todolistWithTwoTasks = {
        tasks: [taskCreated, taskCompleted],
        actions: [priorizationAction]
      };

      // When
      renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

      // Then
      const buttons = screen.getAllByRole('button', { name: 'Prioritize' });
      expect(buttons).toHaveLength(2);
    });

    it('doesnt allow to prioritize tasks if not available', () => {
      // Given
      const todolistWithTwoTasks = {
        tasks: [taskCreated, taskCompleted],
        actions: []
      };

      // When
      renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

      // Then
      const buttons = screen.queryAllByRole('button', { name: 'Prioritize' });
      expect(buttons).toHaveLength(0);
    });

    it('emits prioritize task action', () => {
      // Given
      jest
        .spyOn(todolistRepository, 'useTodolistAction')
        .mockImplementation(() => ({ mutate: todolistActionSpy } as any));
      const todolistWithTwoTasks = {
        tasks: [taskCreated],
        actions: [priorizationAction]
      };

      // When
      renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });
      const prioritizeButton = screen.getByRole('button', { name: 'Prioritize' });
      prioritizeButton.click();

      // Then
      expect(todolistActionSpy).toHaveBeenCalledTimes(1);
      expect(todolistActionSpy).toHaveBeenCalledWith({
        action: priorizationAction,
        task: taskCreated
      });
    });
  });

  describe('Task deprioritization', () => {
    it('allows to deprioritize tasks if available', () => {
      // Given
      const todolistWithTwoTasks = {
        tasks: [taskCreated, taskCompleted],
        actions: [depriorizationAction]
      };

      // When
      renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

      // Then
      const buttons = screen.getAllByRole('button', { name: 'Deprioritize' });
      expect(buttons).toHaveLength(2);
    });

    it('doesnt allow to deprioritize tasks if not available', () => {
      // Given
      const todolistWithTwoTasks = {
        tasks: [taskCreated, taskCompleted],
        actions: []
      };

      // When
      renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

      // Then
      const buttons = screen.queryAllByRole('button', { name: 'Deprioritize' });
      expect(buttons).toHaveLength(0);
    });

    it('emits deprioritize task action', () => {
      // Given
      jest
        .spyOn(todolistRepository, 'useTodolistAction')
        .mockImplementation(() => ({ mutate: todolistActionSpy } as any));
      const todolistWithTwoTasks = {
        tasks: [taskCreated],
        actions: [depriorizationAction]
      };

      // When
      renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

      const deprioritizeButton = screen.getByRole('button', { name: 'Deprioritize' });
      deprioritizeButton.click();

      // Then
      expect(todolistActionSpy).toHaveBeenCalledTimes(1);
      expect(todolistActionSpy).toHaveBeenCalledWith({
        action: depriorizationAction,
        task: taskCreated
      });
    });
  });

  describe('Add a task', () => {
    it('allows to add a task if available', () => {
      // Given
      const todolistWithTwoTasks = {
        tasks: [taskCreated, taskCompleted],
        actions: [addTaskAction]
      };

      // When
      renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });
      const input = screen.getByRole('textbox');

      // Then
      expect(input).toBeVisible();
      expect(input).toHaveAttribute('placeholder', 'Add a task');
    });

    it('doesnt allow to add task if not available', () => {
      // Given
      const todolistWithTwoTasks = {
        tasks: [taskCreated, taskCompleted],
        actions: []
      };

      // When
      renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });
      const input = screen.queryByRole('textbox');

      // Then
      expect(input).toBeNull();
    });

    it('emits add task action', () => {
      // Given
      jest
        .spyOn(todolistRepository, 'useAddTaskAction')
        .mockImplementation(() => ({ mutate: addTaskActionSpy } as any));

      const description = 'Rinté vire chpetit';
      const todolistWithTwoTasks = {
        tasks: [taskCreated],
        actions: [addTaskAction]
      };

      // When
      renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });
      const input = screen.queryByRole('textbox');
      input && fireEvent.change(input, { target: { value: description } });
      expect(addTaskActionSpy).toHaveBeenCalledTimes(0);

      input && fireEvent.keyDown(input, { key: 'Enter', code: 'Enter' });

      // Then
      expect(addTaskActionSpy).toHaveBeenCalledTimes(1);
      expect(addTaskActionSpy).toHaveBeenCalledWith({
        action: addTaskAction,
        task: {
          description
        }
      });
    });
  });

  // todo: test it with playright
  // describe('Integration test', () => {
  //   it('shows tasks from todolist', async () => {
  //     // Given
  //     process.env.SERVICE_URL = SERVICE_HREF;
  //     nock(process.env.SERVICE_URL).get('/todolist').reply(200, todolistDtoWithTwoTasks);
  //
  //     // When
  //     renderWithStore(<Todolist />);
  //
  //     // Then
  //     await waitFor(() => {
  //       expect(screen.getByText('Allé fumé du canon avec Gégé')).toBeVisible();
  //       expect(screen.getByText("Saquer àl'pec avec chgros")).toBeVisible();
  //     });
  //   });
  //   it('prioritizes a task', async () => {
  //     // Given
  //     process.env.SERVICE_URL = SERVICE_HREF;
  //     nock(process.env.SERVICE_URL)
  //       .post('/todolist/prioritize/task')
  //       .reply(200, todolistDtoWithTwoTasks);
  //
  //     // When
  //     renderWithStore(<Todolist />, { todolist: todolistExample });
  //     const prioritizeButton = screen.getByRole('button', { name: 'Prioritize' });
  //     prioritizeButton.click();
  //
  //     // Then
  //     await waitFor(() => {
  //       expect(screen.getByText('Allé fumé du canon avec Gégé')).toBeVisible();
  //       expect(screen.getByText("Saquer àl'pec avec chgros")).toBeVisible();
  //     });
  //   });
  //   it('deprioritizes a task', async () => {
  //     // Given
  //     process.env.SERVICE_URL = SERVICE_HREF;
  //     nock(process.env.SERVICE_URL)
  //       .post('/todolist/deprioritize/task')
  //       .reply(200, todolistDtoWithTwoTasks);
  //
  //     // When
  //     renderWithStore(<Todolist />, { todolist: todolistExample });
  //     const deprioritizeButton = screen.getByRole('button', { name: 'Deprioritize' });
  //     deprioritizeButton.click();
  //
  //     // Then
  //     await waitFor(() => {
  //       expect(screen.getByText('Allé fumé du canon avec Gégé')).toBeVisible();
  //       expect(screen.getByText("Saquer àl'pec avec chgros")).toBeVisible();
  //     });
  //   });
  // });
});
