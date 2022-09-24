# language: fr
Fonctionnalité: Reordonner une tâche de ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir réordonner une tâche
  Afin de la reprioriser

  Reprioriser signifie qu'elle peut être priorisée ou dépriorisée

  Scénario: Prioriser une tâche
    Etant donné une todolist contenant "Promener le chien" et Faire le café
    Lorsque la tâche "Faire le café" est prorisée
    Alors la todolist contient "Faire le café"et Promener le chien

  Scénario: Déprioriser une tâche
    Etant donné une todolist contenant "Promener le chien" et Faire le café
    Lorsque la tâche "Promener le chien" est dépriorisée
    Alors la todolist contient "Faire le café"et Promener le chien
