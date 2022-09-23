# language: fr
Fonctionnalité: Modifier une tache de ma todolist

  En tant qu'utilisateur
  Je souhaite pouvoir modifier une tâche
  Afin de la corriger

  Scénario: Modifier une tache
    Etant donné une todolist contenant "Nettoyer la maison" et Promener le chien
    Lorsque la tache "Nettoyer la maison" est modifée en Tout nettoyer
    Alors la todolist contient Tout nettoyer et Promener le chien
