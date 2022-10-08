#language: fr
Fonctionnalité: Ouverture de l'application

  En tant qu'utilisateur
  Je souhaite pouvoir ouvrir l'application
  Afin d'organiser mes taches à faire

  Scénario: Première ouverture de l'application
    Quand l'application est ouverte
    Alors aucune tâche n'est proposée

  Scénario: Première ouverture de l'application
    Etant donné les tâches à faire
      | Chanter du Joe Dassin      |
      | Siffler avec Yvette Horner |
    Quand l'application est ouverte
    Alors les tâches à faire sont
      | Chanter du Joe Dassin      |
      | Siffler avec Yvette Horner |
