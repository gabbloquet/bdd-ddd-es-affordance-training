# language: fr
Fonctionnalité: Accomplir une tâche de ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir accomplir une tâche
  Afin de me souvenir des tâches accomplies

  Scénario: Todolist contenant deux tâches à faire
    Etant donné les tâches "Nettoyer la maison" et "Promener le chien" à faire
    Lorsque la tâche "Promener le chien" est accomplie
    Alors la tâche "Promener le chien" est terminée
    Et la tâche "Promener le chien" est placée en haut de la liste

  Scénario: Todolist contenant une tâche terminée et deux tâches à faire
    Etant donné une tâche terminée "Nettoyer la maison"
    Et les tâches "Promener le chien" et "Faire le café" à faire
    Lorsque la tâche "Faire le café" est accomplie
    Alors la tâche "Faire le café" est accomplie
    Et la tâche "Faire le café" est placée en seconde position de la liste

