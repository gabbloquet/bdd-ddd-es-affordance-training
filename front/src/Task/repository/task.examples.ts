export const taskExample = {
  id: '1a8d707e-506a-4923-a688-66cbc5674f13',
  description: 'Allé fumé du canon avec Gégé',
  completed: false,
  creationTime: '2022-10-15T09:04:17.725932205',
  duration: null,
  _links: {
    getOrDeleteTask: {
      href: 'http://localhost:8080/tasks/1a8d707e-506a-4923-a688-66cbc5674f13',
      title: 'Get or delete a task'
    },
    deleteTask: {
      href: 'http://localhost:8080/tasks/1a8d707e-506a-4923-a688-66cbc5674f13',
      title: 'Delete a task'
    },
    renameTask: {
      href: 'http://localhost:8080/tasks/1a8d707e-506a-4923-a688-66cbc5674f13/rename',
      title: 'Rename a task'
    },
    completeTask: {
      href: 'http://localhost:8080/tasks/1a8d707e-506a-4923-a688-66cbc5674f13/complete',
      title: 'Complete a task'
    },
    addTask: {
      href: 'http://localhost:8080/tasks',
      title: 'Add a task'
    },
    todolist: {
      href: 'http://localhost:8080/todolist',
      title: 'Get todolist',
      name: 'Get all tasks, todolist'
    }
  },
  _templates: {
    completeTask: {
      method: 'PUT',
      properties: [],
      target: 'http://localhost:8080/tasks/1a8d707e-506a-4923-a688-66cbc5674f13/complete'
    },
    default: {
      method: 'DELETE',
      properties: [],
      target: 'http://localhost:8080/tasks/1a8d707e-506a-4923-a688-66cbc5674f13'
    },
    renameTask: {
      method: 'PUT',
      properties: [
        {
          name: 'description',
          readOnly: true,
          type: 'text'
        }
      ],
      target: 'http://localhost:8080/tasks/1a8d707e-506a-4923-a688-66cbc5674f13/rename'
    },
    deleteTask: {
      method: 'DELETE',
      properties: [],
      target: 'http://localhost:8080/tasks/1a8d707e-506a-4923-a688-66cbc5674f13'
    },
    addTask: {
      method: 'POST',
      properties: [
        {
          name: 'description',
          readOnly: true,
          type: 'text'
        }
      ],
      target: 'http://localhost:8080/tasks'
    }
  }
};
