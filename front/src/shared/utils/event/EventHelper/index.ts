import { TodolistAction } from '../../../../Todolist/model/todolist.model';
import { Task } from '../../../../Task/model/task.model';

export default class CommandHelper {
  // static on(eventName: string, listener: EventListener): void {
  //   document.addEventListener(eventName, listener);
  // }
  //
  // static off(eventName: string, listener: EventListener): void {
  //   document.removeEventListener(eventName, listener);
  // }

  static emit(action: TodolistAction, task: Task): void {
    // const event = new CustomEvent(eventName, { detail: data });
    // document.dispatchEvent(event);
  }
}
