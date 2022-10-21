import axios from 'axios';
import * as nock from 'nock';
import { screen, waitFor } from '@testing-library/react';
import { renderWithStore } from '../shared/utils/test-utils';
import { taskCompleted, taskCreated } from '../Task/model/task.model';
import { depriorizationAction, priorizationAction } from './model/todolist.model';
import { Todolist } from './index';
import { todolistDtoWithTwoTasks } from './repository/todolist.dtos';

axios.defaults.adapter = require('axios/lib/adapters/http');

describe('Todolist', () => {
  afterEach(() => jest.resetAllMocks());

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
      tasks: [taskCreated, taskCompleted]
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
    it('emits prioritize task command', () => {
      // Given
      const todolistWithTwoTasks = {
        tasks: [taskCreated],
        actions: [priorizationAction]
      };

      // When
      renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

      const prioritizeButton = screen.getByRole('button', { name: 'Prioritize' });
      prioritizeButton.click();

      // Then
      expect(emitCommandSpy).toHaveBeenCalledTimes(1);
      expect(emitCommandSpy).toHaveBeenCalledWith(priorizationAction, taskCreated);
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
    it('emits deprioritize task command', () => {
      // Given
      const todolistWithTwoTasks = {
        tasks: [taskCreated],
        actions: [depriorizationAction]
      };

      // When
      renderWithStore(<Todolist />, { todolist: todolistWithTwoTasks });

      const deprioritizeButton = screen.getByRole('button', { name: 'Deprioritize' });
      deprioritizeButton.click();

      // Then
      expect(emitCommandSpy).toHaveBeenCalledTimes(1);
      expect(emitCommandSpy).toHaveBeenCalledWith(depriorizationAction, taskCreated);
    });
  });

  describe('Integration test', () => {
    it('shows tasks from todolist', async () => {
      // Given
      process.env.SERVICE_URL = 'https://my-wonderful-todolist.fr';
      nock(process.env.SERVICE_URL).get('/todolist').reply(200, todolistDtoWithTwoTasks);

      // When
      renderWithStore(<Todolist />);

      // Then
      await waitFor(() => {
        expect(screen.getByText('Allé fumé du canon avec Gégé')).toBeVisible();
        expect(screen.getByText("Saquer àl'pec avec chgros")).toBeVisible();
      });
    });
  });
});
