import CommandHelper from './index';
import { priorizationAction } from '../../../../Todolist/model/todolist.model';
import { taskCreated } from '../../../../Task/model/task.model';

describe('commandHelper', () => {
  describe('handle', () => {
    it('handles dispatched commands', () => {
      jest.spyOn(document, 'addEventListener');
      const listenerMock = jest.fn();

      CommandHelper.handle(listenerMock);

      expect(document.addEventListener).toHaveBeenCalledWith('command', listenerMock);
    });
  });

  describe('emit', () => {
    it('dispatches event when emit command', () => {
      jest
        .spyOn(global, 'CustomEvent')
        .mockImplementation((type: string, data?: any) => ({ type, ...data }));
      jest.spyOn(document, 'dispatchEvent').mockImplementation();

      CommandHelper.emit(priorizationAction, taskCreated);

      expect(document.dispatchEvent).toHaveBeenCalledWith({
        detail: { action: priorizationAction, data: taskCreated },
        type: 'command'
      });
    });
  });
});
