# language: fr
Fonctionnalité: Reordonner une tâche de ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir réordonner une tâche
  Afin de la reprioriser

  Reprioriser signifie qu'elle peut être priorisée ou dépriorisée

  Scénario: Prioriser une tâche
    Etant donné les tâches "Promener le chien" et "Faire le café" à faire
    Lorsque la tâche "Faire le café" est prorisée
    Alors les tâches "Faire le café" et "Promener le chien" sont à faire

  Scénario: Déprioriser une tâche
    Etant donné les tâches "Promener le chien" et "Faire le café" à faire
    Lorsque la tâche "Promener le chien" est dépriorisée
    Alors les tâches "Faire le café" et "Promener le chien" sont à faire
