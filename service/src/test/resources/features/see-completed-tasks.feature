# language: fr
Fonctionnalité: Voir les tâches terminées

  En tant qu'utilisateur
  Je souhaite pouvoir voir les tâches terminées
  Afin de voir le travail accompli

  Scénario: Todolist ayant une tâche terminée
    Etant donné que la tâche "Pratiquer l'event sourcing" est accomplie
    Et la tâche "Continuer à travailler l'event sourcing" est à faire
    Lorsque la todolist est filtrée par tâches accomplies
    Alors la tâche "Pratiquer l'event sourcing" est affichée
