------------------------------------------------------------------------

PROJECT TITLE: TP LETTER

------------------------------------------------------------------------

AUTHORS: PHU Valentin - VALET Tristan - Grp3 L3 - INFO

------------------------------------------------------------------------

VERSION or DATE: Version 1 - 11/2019

------------------------------------------------------------------------

PURPOSE OF PROJECT:
    The purpose of the project:
        - Create differents simulations about the distribution of letters in a city

------------------------------------------------------------------------

HOW TO START THIS PROJECT:

    In first donwlaod Maven in your shell and clone our project:
        $sudo apt-get install maven
        $git clone https://gitlab-etu.fil.univ-lille1.fr/valet/phu-valet-coo.git
    
    After, enter in the repertory with the project COO-LETTER:
    $cd COO-LETTER

    Now we use Maven command to compile our project;
        $mvn package

    To execute different simulations, you must have change in the file pom.xml the line 57 in the balise <mainClass>mail.Main</mainClass>
        for execute the classical Main.java dont change
        for execute the MainFoolLetter.java change <mainClass>mail.Main</mainClass> to <mainClass>mail.MainFoolLetter</mainClass>
    
    This command compile our code and execut our tests

    To have a documentation, use this command:
        $mvn javadoc:javadoc
    
    To make an .jar executable:
        $java -jar target/COO-LETTER-1.0-SNAPSHOT.jar

    And if you clean our project:
        $mvn clean

------------------------------------------------------------------------

COMPTE-RENDU

Dans ce projet, nous avons fait en sorte de respecter le plus possible les principes SOLID vue en cours.
On a de même essaye le pair programming qui s'avère fortement utile pour sortir d'une impasse.

En effet, on peut voir la creation de differentes classes tel que Letter.
Cela nous permets de rajouter à notre guise de nouveaux objets de type Letter, sans pour autant toucher au code dejà prèsent.
Cela permet de garder le principe open-closed. 
De plus, nous avons essayé de faire des methodes le plus simple possible qui possède une responsabilité unique pour garder le principe SOLID du même nom.

Cependant notre Main.java ne respecte pas tout a fait le principe d'Open Closed Principle, en effet etant donne que c'est une simulation et qu'on ne trouvait pas de solution qui puissent respecter le dit principe. Nous avons choisi de creer des instances d'objets que nous avons deja defini.
Pour ajouter des nouvelles instances dans la simulation, il faudra donc ajuster le code du Main.java

Pour executer differentes simulations nous avons pas trouver le moyen de creer different .jar donc nous expliquons dans le
HOW TO START au dessus comment faire.

Des diagrammes UML sont disponibles dans le dossier UML en format PNG pour plus de details.

Question sur les millionaires a propos des FoolLetter:
    Par hypothèse que 100% des personnes ayant reçu la lettre répondent.
    Il y aura 4 generations de lettres envoyées où l'habitant reste sur la liste, et tout cela est envoye a 10 personnes à chaque fois.
    Donc l'expediteur initial peut recevoir au maximum 10^4 lettres de retour contenant dans la simulation 5€ soit 50K€ au maximum.
    Cela reste dans l'hypothèse bien entendu que le la liste d'habitants reste à 4, si cela augmente la somme totale augmentera et par conséquent cela reste possible qu'un millionnaire apparaise.

    Cependant dans notre cas, vu que la chance de réponse est de 10%, soit 1 personnes sur les 10 personnes qui reçoivent la lettre, il  ne recevra que très peu d'argent vu que très peu de personne ne réponds. Il sera difficile à lui d'être bénéficiaire.
    Et les générations de lettres va vite disparaitre vu qu'il y a 1 chance sur 10 qu'elle perdurent à chaque génération.
    Donc 10% d'avoir une première génération, 1% d'avoir une seconde, 0,1% d'en avoir une troisième, etc...

    Si on veut voir des millionaires tout en gardant la même somme de dons, il faut donc changer le nombre de personnes reçevant de l'argent ainsi que la probabilité que les personnes la renvoient augmente.
    Mais dans le cas de l'énoncé, personne ne sera millionaire.