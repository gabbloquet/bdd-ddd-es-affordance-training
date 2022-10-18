import * as nock from 'nock';
import axios from 'axios';
import { emptyDtoTodolist } from './todolist.dtos';
import { getTodolist } from './todolist.repository';

axios.defaults.adapter = require('axios/lib/adapters/http');

describe('Todolist repository', () => {
  it('gets todolist', async () => {
    // Given
    process.env.SERVICE_URL = 'https://my-wonderful-todolist.fr';
    nock(process.env.SERVICE_URL).get('/todolist').reply(200, emptyDtoTodolist);

    // When
    const todolist = await getTodolist();

    // Then
    const expectedMappedTodolist = {
      tasks: []
    };
    expect(todolist).toStrictEqual(expectedMappedTodolist);
  });
});
