import * as nock from 'nock';
import axios from 'axios';
import { emptyDtoTodolist, SERVICE_HREF, todolistDtoWithTwoTasks } from './todolist.dtos';
import { getTodolist, todolistAction } from './todolist.repository';
import * as todolistMapper from './todolist.mapper';
import { todolistExample } from '../model/todolist.model';
import { taskCreated } from '../../Task/model/task.model';
import { HTTP_METHOD } from '../../shared/types/hateoas.types';

axios.defaults.adapter = require('axios/lib/adapters/http');

describe('Todolist repository', () => {
  const toTodolistSpy = jest
    .spyOn(todolistMapper, 'toTodolist')
    .mockReturnValueOnce(todolistExample);

  it('gets todolist', async () => {
    // Given
    process.env.SERVICE_URL = 'https://my-wonderful-todolist.fr';
    nock(process.env.SERVICE_URL).get('/todolist').reply(200, emptyDtoTodolist);

    // When
    const todolist = await getTodolist();

    // Then
    expect(toTodolistSpy).toHaveBeenCalledTimes(1);
    expect(toTodolistSpy).toHaveBeenCalledWith(emptyDtoTodolist);

    expect(todolist).toStrictEqual(todolistExample);
  });

  it('prioritizes a task', async () => {
    // Given
    process.env.SERVICE_URL = 'https://my-wonderful-todolist.fr';
    const id = 'wonderful-id';
    const priorizationAction = {
      method: HTTP_METHOD.POST,
      name: 'Prioritize',
      url: 'https://my-wonderful-todolist.fr/todolist/prioritize/task',
      properties: [
        {
          name: 'id'
        }
      ]
    };
    nock(process.env.SERVICE_URL)
      .post('/todolist/prioritize/task', { id: 'wonderful-id' })
      .reply(200, todolistDtoWithTwoTasks);

    // When
    const todolist = await todolistAction(priorizationAction, {
      ...taskCreated,
      id
    });

    // Then
    expect(toTodolistSpy).toHaveBeenCalledTimes(1);
    expect(toTodolistSpy).toHaveBeenCalledWith(todolistDtoWithTwoTasks);

    expect(todolist).toStrictEqual(todolistExample);
  });
});
