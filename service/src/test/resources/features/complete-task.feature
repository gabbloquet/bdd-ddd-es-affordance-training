# language: fr
Fonctionnalité: Accomplir une tache de ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir accomplir une tâche
  Afin de me souvenir des tâches accomplies

  Scénario: Todolist contenant deux taches à faire
    Etant donné les taches Nettoyer la maison et Promener le chien à faire
    Lorsque la tache Promener le chien est accomplie
    Alors la tache Promener le chien est terminée
    Et la tache Promener le chien est placée en haut de la liste

  Scénario: Todolist contenant une tache terminée et deux taches à faire
    Etant donné une tache terminée Nettoyer la maison
    Et les taches Promener le chien et Faire le café à faire
    Lorsque la tache Faire le café est accomplie
    Alors la tache Faire le café est accomplie
    Et la tache Faire le café est placée en seconde position de la liste

