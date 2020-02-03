------------------------------------------------------------------------

PROJECT TITLE: TP QUIZZ

------------------------------------------------------------------------

AUTHORS: PHU Valentin - VALET Tristan - Grp3 L3 - INFO

------------------------------------------------------------------------

VERSION or DATE: Version 1 - 11/2019

------------------------------------------------------------------------

PURPOSE OF PROJECT:
    The purpose of the project:
        - Create a quizz interface (shell or graphical) from a file with specified format.

------------------------------------------------------------------------

HOW TO START THIS PROJECT:

    In first donwload Maven in your shell and clone our project:
        $sudo apt-get install maven
        $git clone https://gitlab-etu.fil.univ-lille1.fr/valet/phu-valet-coo.git
    
    After, enter in the repertory with the project COO-QUESTION:
    $cd COO-QUESTION

    Now we use Maven command to compile our project;
        $mvn package
    
    This command compile our code and execut our tests

    To have a documentation, use this command:
        $mvn javadoc:javadoc
    
    To make an .jar executable:
        $java -jar target/COO-QUESTION-1.0-SNAPSHOT.jar [optionnal arguments] filename

    And if you clean our project:
        $mvn clean

    You can test the executable with a file in the src/other/HarryPotter_quiz.txt
    For shell quizz:
        $java -jar target/COO-QUESTION-1.0-SNAPSHOT.jar src/other/HarryPotter_quiz.txt
    For graphical quizz:
        $java -jar target/COO-QUESTION-1.0-SNAPSHOT.jar -g src/other/HarryPotter_quiz.txt

    If you want create your self quizz, refering to "HOW TO HAVE A CORRECT TXT'S QUIZZ" rubrique. 

------------------------------------------------------------------------

HOW TO HAVE A CORRECT TXT'S QUIZZ:

    The file needs a x*4 lines.

    The First is the statement of questions.
    The Second is the answer (answers are differents depending the questions, more details after).
    The Third is the type of question.
    The Fourth is the number of points.
    And again and again and again...

    Type of questions and the answer expected :
        * YesNoAnswer : "yes" ou "oui" ou "no" ou "non"
        * TextualAnswer : A simple word or text
        * NumericAnswer : A number
        * MultipleAnswer : Differents texts/words split with ";"
        * UniqueMultipleAnswer : Differents texts/words split with "|"

------------------------------------------------------------------------

COMPTE-RENDU

Pour ce projet, nous avons bien évidemment suivi les principes SOLID.

-- CONCEPTION --

Pour la conception, nous avons opté pour des singletons pour 4 classes : 
AnswerFactory, QuizzFactory, GraphicalQuizzFactory et ScannerChoice(l'objet qui permet de scanner l'input du player).
En effet, ces élements n'ayant qu'une seule fonction, il n'est pas nécessaire d'avoir plusieurs instance de ces derniers.
Pour plus d'informations, ne pas hésiter à voir la doc.

-- FORMAT DU FICHIER --

Nous avons de même décidé d'avoir un format de fichier de quizz avec une ligne supplémentaire qui est le type de la question.
Nous avons donc maintenant ce format :  * ## Ennoncé de la question
                                        * ## Réponse(s)
                                        * ## Type de la question
                                        * ## Nombre de points de la question
                                        * ## etc
Il faut bien entendu garder ce format ou le programme ne fonctionne pas.
Ne pas se tromper dans le type de la question, il existe :
TextualAnswer, NumericAnswer, YesNoAnswer, MultipleAnswer and UniqueMultipleAnswer
Et bien faire attention que le format de la réponse correspond au type.  
Pour plus d'informations, ne pas hésiter à voir la doc.

Ce choix, nous permet d'utiliser la réfléxivité pour chercher l'objet à créer et avoir le bon type.

-- GESTION DES PANELS --

La réfléxivité a aussi été utilisé lors de la conception des panels des questions.
En effet, nous avons choisi pour cela de leur donner le nom du type de la question + "Panel".
Il nous suffit juste au moment de la création du panel de prendre le nom de classe de la réponse, et ensuite, chercherle constructeur "Panel" relatif à celui-ci.
Par exemple, nous avons une question de type "YesNoAnswer", nous prenons son nom et créons une instance de "YesNoAnswerPanel".

Notre jar possède la possibilité de mettre un argument -s ou -g pour le mode shell ou graphique (-s par defaut si pas d'argument)

-- AUTRE --

Nous n'avons pas affiné l'aspect graphique pour le rendre plus esthetique. 
Nous pensons que ce n'était pas la priorité. Tout est fonctionnel et donc ceci est le plus important.

-- TESTS --

Nous avons ajouté des attributs 'in' et 'ou' pour la sortie de ScannerChoice et Quizz, nous permettant de tester les inputs d'un joueur.
De plus, pour les tests côté graphique, ne sachant pas comment faire des tests unitaires, nous avons décidé de faire des méthodes main pour chaque classe concernant cette aspect et ainsi tester le bon fonctionnement.