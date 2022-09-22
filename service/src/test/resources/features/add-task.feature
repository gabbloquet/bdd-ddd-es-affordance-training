# language: fr
Fonctionnalité: Ajouter une tache à ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir ajouter des tâches
  Afin de me souvenir de mes tâches à faire

  Scénario: Todolist vierge
    Etant donné une todolist vierge
    Lorsque la tache "Travailler le TDD" est ajoutée
    Alors la todolist contient "Travailler le TDD"


  Scénario: Todolist contenant une tache
    Etant donné une todolist contenant la tache "Travailler le BDD"
    Lorsque la tache "Travailler le hula hoop" est ajoutée
    Alors la todolist contient "Travailler le BDD" et "Travailler le hula hoop"
