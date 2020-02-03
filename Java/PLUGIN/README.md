------------------------------------------------------------------------

PROJECT TITLE: TP PLUGIN

------------------------------------------------------------------------

AUTHORS: PHU Valentin - VALET Tristan - Grp3 L3 - INFO

------------------------------------------------------------------------

VERSION or DATE: Version 1 - 12/2019

------------------------------------------------------------------------

PURPOSE OF PROJECT:
    The purpose of the project:
        - Create a Plugin interface.

------------------------------------------------------------------------

HOW TO START THIS PROJECT:

    In first donwload Maven in your shell and clone our project:
        $sudo apt-get install maven
        $git clone https://gitlab-etu.fil.univ-lille1.fr/valet/phu-valet-coo.git
    
    After, enter in the repertory with the project COO-PLUGIN:
    $cd COO-PLUGIN

    Now we use Maven command to compile our project;
        $mvn package
    
    This command compile our code and execut our tests

    To have a documentation, use this command:
        $mvn javadoc:javadoc
    
    To launch the program:
        $java -cp target/classes:repository plugin.graphical.Main &

    To add some plugins, the available plugins are in "target/classes/plugins/" directory when the project is compiled.
    Move the differents ".class" files in the "repository/plugins" in the project's root.
    You can move or remove when the program is on progress.
    To help you, this command to copy the files:
        $cp target/classes/plugins/<classNameFile>.class repository/plugins/

    To remove plugin:
        $rm repository/plugins/<classNameFile>.class

    And if you clean our project:
        $mvn clean

------------------------------------------------------------------------

COMPTE-RENDU

Pour ce projet, nous avons bien évidemment suivi les principes SOLID.

Nous avons suivi les consignes et les exercices de l'énoncéavec succés, et n'avons laissé que la partie du projet complet avec quelques exemples de plugins comme ToLowerCase.java par exemple.

Pour les tests, dû au manque de temps avec l'approche des partiels, n'ont pas été réalisés. Cependant la reflexion et la recherche a été fait. Pour les tests, de FileChecker, nous avons trouvé la class TemporaryFolder pour créer un dossier temporaire pour le texte. Il suffit de créer un mock de file pour texter la bonne execution du check.

Pour la conception, on peut constater la conception grâce au diagramme UML mis à disposition dans le dossier "uml". Il a bien évidemment été question du concept patern Observer.

Le projet est opérationnel même sans les tests réalisés.