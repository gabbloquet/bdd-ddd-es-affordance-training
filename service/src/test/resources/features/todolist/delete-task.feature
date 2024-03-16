# language: fr
Fonctionnalité: Supprimer une tâche de ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir supprimer une tâche
  Afin de ne plus l'avoir dans le travail à faire

  Ceci ne signifie pas qu'elle a été faite, ceci signifie que nous ne voulons plus la faire.

  Scénario: Todolist contenant deux tâches à faire
    Etant donné les tâches à faire
      | Description        |
      | Nettoyer la maison |
      | Promener le chien  |
    Lorsque la tâche "Promener le chien" est supprimée
    Alors la tâche "Nettoyer la maison" est à faire

