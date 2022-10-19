import * as nock from 'nock';
import axios from 'axios';
import { emptyDtoTodolist } from './todolist.dtos';
import { getTodolist } from './todolist.repository';
import * as todolistMapper from './todolist.mapper';
import { todolistExample } from '../model/todolist.model';

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
});
