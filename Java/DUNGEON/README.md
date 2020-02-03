------------------------------------------------------------------------

PROJECT TITLE: TP DUNGEON

------------------------------------------------------------------------

AUTHORS: PHU Valentin - VALET Tristan - Grp3 L3 - INFO

------------------------------------------------------------------------

VERSION or DATE: Version 1 - 09/2019

------------------------------------------------------------------------

PURPOSE OF PROJECT:
    The purpose of the project:
        - Create a dungeon game

------------------------------------------------------------------------

HOW TO START THIS PROJECT:

    In first donwlaod Maven in your shell and clone our project:
        $sudo apt-get install maven
        $git clone https://gitlab-etu.fil.univ-lille1.fr/valet/phu-valet-coo.git
    
    After, enter in the repertory with the project COO-DUNGEON:
    $cd COO-DUNGEON

    Now we use Maven command to compile our project;
        $mvn package
    
    This command compile our code and execut our tests

    To have a documentation, use this command:
        $mvn javadoc:javadoc
    
    To make an .jar executable:
        $java -jar target/COO-DUNGEON-1.0-SNAPSHOT.jar

    And if you clean our project:
        $mvn clean

------------------------------------------------------------------------

COMPTE-RENDU

Dans ce projet, nous avons fait en sorte de respecter le plus possible les principes SOLID vue en cours.

En effet, on peut voir la création de différentes classes/enum tel que AllItems, AllMonsters, AllActions.
Cela nous permets de rajouter à notre guise de nouveaux objets, actions ou monstres, sans pour autant toucher au code déjà prèsent.
Cela permet de garder le principe open-closed. 
Cependant il faut quand même rajouter le nouvel element dans les dites classes pour avoir la possibilité de l'utiliser.
Mais cela reste minime comparé à devoir changer le code même du programme.
(Pour savoir comment ajouter, se referer à la documentation des classes)

Nous avons aussi créer une classe abstraite "InteractivePeople" pour "OneArmedBandit" qui implemente l'interface "Items".
Cela nous a permis de différencier les items car les objets de type "humain" ne peut pas donner d'autres "humains".
Cela permettra par la suite, si on le souhaite de créer de nouveaux "humains" tel que des marchands ou encore des personnes donnant un item gratuit. Libre cours à l'imagination.

Certains points n'ont hélas pas été fait, tel que le test de OneArmedBandit car vu l'aspect aléatoire de son comportement,
nous n'avons pas su comment le réaliser.
De plus, les tests des classes qui ne renvoient rien mais affiche du texte n'a pas été fait mais en contrepartie 
les Main de ces classes nous ont permis de les réaliser (c.f. main de scannerChoice.java).

Nous avons réalisé un ajout d'une nouvelle action "Status" qui donne l'état du joueur pour tester l'ajout de nouvel action.

Pour l'algorithme de génération du dungeon, nous avons décidé de ne pas implémenter le fait que lors qu'on tourne par exemple toujours
à gauche, nous retombons sur la même salle.
Nous estimons que nous descendons plus profondement dans le donjon donc ne sommes pas dans les mêmes salles si on tourne toujours à gauche.

Des diagrammes UML sont disponibles dans le dossier UML en format PNG pour plus de détails.
