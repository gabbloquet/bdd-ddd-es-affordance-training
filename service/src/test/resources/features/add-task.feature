# language: fr
Fonctionnalité: Ajouter une tâche à ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir ajouter des tâches
  Afin de me souvenir de mes tâches à faire

  Scénario: Todolist vierge
    Etant donné aucune tâche à faire
    Lorsque la tâche "Travailler le TDD" est ajoutée
    Alors la tâche "Travailler le TDD" est à faire


  Scénario: Todolist contenant une tâche
    Etant donné la tâche "Travailler le BDD" à faire
    Lorsque la tâche "Travailler le hula hoop" est ajoutée
    Alors les tâches à faire sont
      | Travailler le BDD     |
      | Travailler le hula hoop |
