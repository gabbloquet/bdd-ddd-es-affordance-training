# language: fr
Fonctionnalité: Prioriser / Déprioriser une tâche de ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir prioriser une tâche
  Afin de marquer son importance

  En tant qu'utilisateur
  Je souhaite pouvoir déprioriser une tâche
  Afin de la mettre au second plan


  Scénario: Prioriser une tâche
    Etant donné les tâches "Promener le chien" et "Faire le café" à faire
    Lorsque la tâche "Faire le café" est prorisée
    Alors les tâches "Faire le café" et "Promener le chien" sont à faire

  Scénario: Déprioriser une tâche
    Etant donné les tâches "Promener le chien" et "Faire le café" à faire
    Lorsque la tâche "Promener le chien" est dépriorisée
    Alors les tâches "Faire le café" et "Promener le chien" sont à faire
