import { TodolistAction } from '../../../../Todolist/model/todolist.model';
import { Task } from '../../../../Task/model/task.model';

const COMMAND = 'command';

export default class CommandHelper {
  static handle(listener: EventListener): void {
    document.addEventListener(COMMAND, listener);
  }

  static emit(action: TodolistAction, task: Task): void {
    const event = new CustomEvent(COMMAND, { detail: { action, data: task } });
    document.dispatchEvent(event);
  }
}
