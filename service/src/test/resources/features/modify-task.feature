# language: fr
Fonctionnalité: Modifier une tâche de ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir modifier une tâche
  Afin de la corriger

  Scénario: Modifier une tâche
    Etant donné les tâches "Nettoyer la maison" et "Promener le chien" à faire
    Lorsque la tâche "Nettoyer la maison" est modifée en "Tout nettoyer"
    Alors les tâches "Tout nettoyer" et "Promener le chien" sont à faire
