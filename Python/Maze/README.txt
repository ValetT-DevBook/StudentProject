########### 		################################
# ENGLISH # 		# French under english version #
########### 		################################

USAGES :
To know all of the API of modules in our project your can open a control terminal in the directory /Maze and type 'make doc' without quotes.
In this documentation you can see :
-The board book which explain each steps to make the project during the month.
-Precise explanations of each modules.

Two main applications are proposed:
To use them you need to open a control terminal in the directory ../Maze/src and launch the script you would between main_maze.py and graphical_main_maze.py) with python3.
Examples:
.../Maze/src : python3 main_maze.py 
.../Maze/src : python3 graphical_main_maze.py

For more details about arguments :
Use the argument -h or --help for a quick help like : 
.../Maze/src : python3 main_maze.py -h

Use the argument -m or --manual for a manual about each arguments like :
.../Maze/src : python3 graphical_main_maze.py -m

--------------------------------------------------------------------------
| Next stars like * are used to comments actions |
EXAMPLES:

Short example with application :
1)
	python3 main_maze.py -e 3 3
	*type : row 	* for the first question*
	*type : 2 	*for the second question*
	*type :  ,  , | *(= 2 spaces,2spaces,space and a |) for the third question*
	*see the result*
	*Now you know how to edit a maze.*

2)
	python3 main_maze.py -r 10 10
	*Generate a random maze with a size of 10 by 10*

3)
	python3 graphical_main_maze.py -r 30 30 -cg green
	*Display a generated random maze with a size of 30 by 30 and with a green goal*

4)
	python3 graphical_main_maze.py -f maze_30_30_4.txt -pm
	*Display the maze in the file maze_30_30_4.txt from the library with a pac-man© theme*

Short examples with the module Maze.py : /!\ Only for programmer /!\ 
5)
	You can use directly the maze module but you need a python interpreter like thonny or idle or use the control terminal and type python3 then from maze import * :
	After running the module you can execute this differentes commands :
	>>> m=Maze()   
	>>> m		
	>>> m.random() 	
	>>> m		
	>>> m.write_in_file("My_first_maze.txt") *You can see your save in the directory : .../Maze/maze_library/saves/ *

In terms of operation, we arranged to debug the most errors we have found, as well as the version you have no problems with.
Thanks for use our API.

############
# FRANCAIS # 
############

UTILISATIONS :
Pour en savoir plus les différents modules de notre projet, vous pouvez ouvrir un terminal de commande, dans le dossier /Maze et tapez 'make doc' sans les guillemets.
Dans cette documentation vous pouvez voir :
-Le livre de bord qui explique toutes les étapes de réalisation de notre projet durant le mois.
-Des explications précises pour chaques modules.

Deux applications principales sont proposées:
Pour s'en servir, vous aurez besoin d'ouvrir un terminal de commande dans le dossier ../Maze/src et de lancer selon votre choix main_maze.py ou graphical_main_maze.py avec python3.
Exemples:
.../Maze/src : python3 main_maze.py 
.../Maze/src : python3 graphical_main_maze.py

Pour plus de détails a propos des arguments:
Utilisez l'argument -h ou --help pour une aide rapide comme : 
.../Maze/src : python3 main_maze.py -h

Utilisez l'argument -m ou --manual pour un manuel pour chaque arguments comme :
.../Maze/src : python3 graphical_main_maze.py -m

------------------------------------------------------------------------------------
| Dans la suite les étoiles comme * seront utilisées pour commenter les actions |

EXEMPLES:
Exemples rapides avec les applications:
1)
	python3 main_maze.py -e 3 3
	*tapez : row * pour la première question *
	*tapez : 2 * pour la seconde question*
	*tapez :  ,  , | (= 2 espaces,2espaces,espace et un |) * pour la troisième question*
	*voyez le résultat*
	*Maintenant vous savez comment éditer un labyrinthe*

2)
	python3 main_maze.py -r 10 10
	*Génère aléatoirement un labyrinthe de taille 10 par 10*

3)
	python3 graphical_main_maze.py -r 30 30 -cg green
	*Affiche un labyrinthe de taille 30 par 30 génèré aléatoirement avec une arrivée verte*

4)
	python3 graphical_main_maze.py -f maze_30_30_4.txt -pm
	*Affiche le labyrinthe dans le fichier maze_30_30_4.txt a partir de la librairie, avec un theme pac-man©*

Exemples rapide avec le module Maze.py : /!\ Seulement pour les programmeurs /!\ 
5)
	Vous pouvez utiliser le module maze directement mais vous aurez besoin soit d'un interpreteur python comme thonny ou idle, soit ouvrez un terminal de commande et tapez python3 puis tapez from maze 		import *:

	Apres avoir lancé le module, vous pouvez éxécuter ces différentes commandes :
	>>> m=Maze()   
	>>> m		
	>>> m.random() 	
	>>> m		
	>>> m.write_in_file("My_first_maze.txt") *Vous pouvez voir votre sauvegarde dans le dossier : .../Maze/maze_library/saves/ *

En terme de fonctionnement, nous nous sommes arrangés pour débogguer le plus d'erreurs que nous avons trouvé, ainsi la version que vous avez ne comporte a priori pas de bogues.
Merci d'utiliser notre API.


Authors\Auteurs :
BONVOISIN Alexandre
PHU Valentin
Valet Tristan
