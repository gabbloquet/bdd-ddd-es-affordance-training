# language: fr
Fonctionnalité: Accomplir une tâche de ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir accomplir une tâche
  Afin de me souvenir des tâches accomplies

  Scénario: Todolist contenant deux tâches à faire
    Etant donné les tâches à faire
      | Description        |
      | Nettoyer la maison |
      | Promener le chien  |
    Lorsque la tâche "Promener le chien" est accomplie
    Alors la tâche "Promener le chien" est terminée
    Et la tâche "Promener le chien" est placée en haut de la liste

  Scénario: Todolist contenant une tâche terminée et deux tâches à faire
    Etant donné la tâche "Nettoyer la maison" terminée
    Et les tâches à faire
      | Description       |
      | Faire le café     |
      | Promener le chien |
    Lorsque la tâche "Faire le café" est accomplie
    Alors la tâche "Faire le café" est accomplie
    Et la tâche "Faire le café" est placée en seconde position de la liste

