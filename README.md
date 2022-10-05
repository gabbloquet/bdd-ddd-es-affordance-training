# BDD, DDD, Event sourcing, Affordance training

This repository is a boot camp to try to implement BDD, DDD, affordance.  

Firstly, I tried to work on a TODO list example.

## Documentation

1. I started by working on [gherkins](./doc/gherkins.md)
2. Then, tried to discover [affordance tools](./doc/affordance.md)
3. Working on an [architecture schema](./doc/architecture.md)
4. [Service development](./service/README.md)

## Next steps

1. Make an amazing todolist service, [more informations here](./service/README.md)
2. Try to implement a front-end to be compliant with it
3. Make the whole app works :tada:
4. Try to work on another example -> _Provide me ideas with [issues](https://github.com/gabbloquet/bdd-ddd-affordance-training/issues)_

## How add a feature

1. Ajouter le / les acceptance tests (au format Gherkins) dans [resources/features](./service/src/test/resources/features)
2. Écrire les Specs associées, proposées par l'auto-completion sur IntelliJ (Choisir Java), avec le code associé
3. Ceci va donc nécéssité la création de nouveaux éléments, comme la commande si il s'agit d'une nouvelle feature, ainsi que du UseCase associé qui catchera la commande, l'événement qu'il va emettre...
4. Il faudra ensuite faire le lien entre ces éléments et leurs parents, par exemple une commande de tache devra `extends TaskCommand` et implémenté le visiteur associé
5. Bravo, votre commande est traitée, elle emet un événement. Maintenant il faut catcher cet élément pour faire réagir les autres sous-domaines de l'application qui doivent l'être. (Exemple, `TaskDeleted` émit, ma todolist doit supprimer l'élément de sa liste)
6. Dans notre cas la `Todolist` est une projection, ceci va `apply` l'événement à elle même, il faut créer cette fonction associée.
7. le State doit lui aussi muté pour accueillir ce nouvel événement et ce l'`apply`. Cf. `TaskState`
8. Attention à bien ajouté les classes telle que le UseCase à la configuration Spring dans le domaine et les tests.
9. Maintenant vous pouvez passer au dévelopmment de l'infra, `Resource` pour entrer dans le domaine (endpoints), `Repository` pour en sortir.

### Architecture

[Architecture documentation](./doc/architecture.md).

### Local

For now this application is just a back office.  
The easiest ways to test it is : 
 - Performs tests [./service/src/test/java](./service/src/test/java) (If you use IntelliJ -> Left click -> `Run 'All Tests'`)
 - [Use this postman collection](https://www.getpostman.com/collections/29ec62bd3b5ed531c0b5) (Copy paste this content in `File` -> `Import` -> `Raw text`)
