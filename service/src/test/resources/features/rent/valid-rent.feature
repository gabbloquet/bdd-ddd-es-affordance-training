# language: fr
Fonctionnalité: Valider une demande de location

  Scénario: Client au téléphone jetable et prix haut
    Etant donné Mr "Tapie" propriétaire d’un téléphone jetable
    Et une "Porsche Tycan" à 1200€ par mois
    Quand Mr "Tapie" demande à louer une "Porsche Tycan"
    Alors sa demande de location est invalidée
    Et Mr Tapie est informé "Numéro de téléphone incompatible"

  Scénario: Client au numéro provisoire et prix bas
    Etant donné Mr "Rocancourt" ayant un numéro provisoire
    Et une "TWINGO E-Tech" à 100€ par mois
    Quand Mr "Roquancourt" demande à louer une "TWINGO E-Tech"
    Alors sa demande de location est validée
