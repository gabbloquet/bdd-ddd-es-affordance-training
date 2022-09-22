# language: fr
Fonctionnalité: Reordonner une tache de ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir réordonner une tâche
  Afin de la reprioriser

  Reprioriser signifie qu'elle peut être priorisée ou dépriorisée

  Scénario: Prioriser une tache
    Etant donné une todolist contenant Promener le chien et Faire le café
    Lorsque la tache Faire le café est prorisée
    Alors la todolist contient Faire le café et Promener le chien

  Scénario: Déprioriser une tache
    Etant donné une todolist contenant Promener le chien et Faire le café
    Lorsque la tache Promener le chien est dépriorisée
    Alors la todolist contient Faire le café et Promener le chien