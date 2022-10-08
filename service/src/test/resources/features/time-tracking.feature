# language: fr
Fonctionnalité: Connaitre le temps passé sur une tache

  En tant qu'utilisateur
  Je souhaite connaitre le temps passé sur une tâche
  Afin de m'aider dans ma prise de décision

  En effet, une tâche ouverte depuis trop de temps montre peut-être qu'elle n'est pas si utile.
  Une tache qui a pris beaucoup de temps à être résolue a peut-être été mal découpée...

#  Scénario: Afficher la durée de la tâche en cours
#    Etant donné les tâches à faire
#      | Description           | Heure de création  |
#      | Aller au parc Asterix | 01/10/2022 à 8:30  |
#      | Aller au bar          | 05/10/2022 à 10:30 |
#    Et la date du jour 08/10/2022 à 11:30
#    Quand les tâches en cours sont demandées
#    Alors les tâches proposées sont
#      | Tâche                 | Durée                   |
#      | Aller au parc Asterix | 7 jour(s) et 3 heure(s) |
#      | Aller au bar          | 3 jour(s) et 1 heure(s) |
#
#  Scénario: Filtrer les tâches terminées
#    Etant donnée les tâches terminées
#      | Description                               | Heure de création  |
#      | Commencer la pratique de l'event sourcing | 06/10/2022 à 8:30  |
#      | Commencer la pratique du DDD              | 06/10/2022 à 9:30  |
#    Et la date du jour 08/10/2022 à 08:30
#    Quand les tâches terminées sont demandées
#    Alors les tâches proposées sont
#      | Tâche                                     | Durée                    |
#      | Commencer la pratique de l'event sourcing | 2 jour(s)                |
#      | Commencer la pratique du DDD              | 1 jour(s) et 23 heure(s) |
