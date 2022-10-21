import {
  Todolist as TodolistState,
  TODOLIST_ACTIONS,
  TodolistAction
} from './model/todolist.model';

export const getActions = (todolist: TodolistState | undefined) => ({
  prioritizeAction:
    todolist && todolist.actions && getAction(todolist.actions, TODOLIST_ACTIONS.PRIORITIZE_TASK),
  deprioritizeAction:
    todolist &&
    todolist.actions &&
    getAction(todolist.actions, TODOLIST_ACTIONS.DEPRIORITIZE_ACTION)
});

const getAction = (
  actions: Array<TodolistAction>,
  actionToFind: TODOLIST_ACTIONS
): TodolistAction | undefined =>
  actions.find((action: TodolistAction) => action.type === actionToFind);
