#Bonvoisin Alexandre
#Phu Valentin 
#Valet Tristan 

#Projet: Nonogramme
from ressources.sous_fonctions import *
import os
import sys
import time
from ressources.tk_nonogram import *
#Dictionnaire contenant les valeurs des couleurs en fonction du nonogramme '.sgriddler' coloré et des couleurs sur tkinter :
dictColor= {'#0': '#FFFFFF', '#1': '#C0C0C0', '#2': '#808080',
            '#3': '#3f3f3f', '#4': '#000000', '#5': '#ADD8E6',
            '#6': '#0000FF', '#7': '#8B4513', '#8': '#FF0000',
            '#9': '#FFA500', '#A': '#FFFF00', '#B': '#800080',
            '#C': '#FFC0CB', '#D': '#00FF00', '#E': '#008000',
            '#F': '#F4A460', }

def grille(fichier,dico,impr):
    """
    Ecris dans le fichier la grille du niveau.
    :param fichier: (str) Chaine de caractère precisant le nom du fichier.
    :param dico: (dict) Dictionnaire contenant les colonnes et les lignes du modèle de nonogramme choisi.
    :param impr: (bool) Indique si l'on veut l'impression du repère sur la grille ou non, (True pour terminal, False pour graphique).
    :return: (None)
    CU: Aucune.
    """
    marge_rows = marge(dico, 'rows')  #Définis les marges verticales et horizontal 
    marge_cols = marge(dico, 'cols')
    
    rows = liste_valeur(dico, 'rows') #Définis les listes des coordonnées
    cols = liste_valeur(dico, 'cols')

    with open(fichier,'wt',encoding='UTF-8') as sortie: #Ouverture du fichier du niveau

        i = marge_cols - 1                        #On prends la marge vertical
        while i >= 0:                             #On fait une boucle qu'on decremente
            sortie.write('   ' * marge_rows)      #Ecriture de l'espacement de la marge
            for j in cols:                        #Pour chaque coordonnées de la liste colonnes, on regarde si la longueur est egale ou superieur à i 
                if len(j) > i:                    #pour savoir si on mets le nombre
                    sortie.write(espacement(j[i]))
                else:
                    sortie.write(' ' * 3) #Sinon on mets 3 espaces car pas de coordonnées
            i -= 1
            sortie.write('\n')
            
        for j in range(len(rows)): #Même chose cette fois ci pour les lignes
            i = marge_rows -1
            while i >= 0:
                if len(rows[j]) > i:
                    sortie.write(espacement(rows[j][i]))
                else:
                    sortie.write(' '*3)
                i -= 1
            sortie.write('  .' * len(cols) + ' \n')

    with open(fichier, 'rt', encoding='UTF-8') as entree:
        liste = entree.readlines()

    if impr:
        impression(liste,marge_cols,marge_rows,cols)
        
def entreeCoord(dico):
    """
    Retourne une liste contenant, le caractère a inserer dans la grille et l'ensemble de coordonnées qui lui associé.
    :param dico: (dict) Dictionnaire contenant les colonnes et les lignes du modèle de nonogramme choisi.
    :return: (liste) Liste de la forme [caractère,(x,y)].
    CU: Aucune
    """
    marge_rows = marge(dico, 'rows')
    marge_cols = marge(dico, 'cols')
    rows = liste_valeur(dico, 'rows')
    cols = liste_valeur(dico, 'cols')

    TX=False
    TY=False         #Variables d'exceptions
    x=len(rows)+1
    y=len(cols)+1    #Variables existantes mais invalide dans le cas ou le try engendre une exception, il n'y aura pas d'erreur
    caract=False
    
    while not caract:
        carac = input('Caractère : (X ou O par exemple) ').upper()
        if (carac =='X') or (carac=='O'):
            caract=True
        else:
            print('Veuillez saisir x,o,X ou O')
        
        if caract==True:
            while not TX or not TY:
                try:
                    try:
                        x = int(input('Coordonnée en x: '))-1
                        y = int(input('Coordonnée en y: '))-1 #Essaye de recuperer un ensemble de coordonnées valides
                    except:
                        pass
                except :
                    print('Vous n\'avez pas saisi un ensemble de coordonnées valide')
                    
                if -1<x and x<= len(rows)-1:
                    TX=True
                    if -1<y and y <= len(cols)-1 :
                        TY=True
                        coord = (y,x)
                        print(coord) #Affiche les coordonnées au joueur
                    else :
                        print('Veuillez saisir un Y compris entre [1 et '+str(len(cols))+']')  #Indique l'erreur  
                else:
                    print('Veuillez saisir un X compris entre [1 et '+str(len(rows))+']') #Indique l'erreur
              
            if TX and TY :            #Si x ET y vrai alors on fait la suite
                return [carac , coord]
            
def edit_grille(dico,coordInput,impr):
    """
    Edite la grille en fonction des input (clics ou saisi clavier) du joueur.
    :param dico: (dict) Dictionnaire contenant les colonnes et les lignes du modèle de nonogramme choisi.
    :param coordInput: (tuple) Coordonnées saisies par le joueur.
    :param impr: (bool) Indique si l'on veut l'impression du repère sur la grille ou non, (True pour terminal, False pour graphique).
    :return: (None)
    CU: Le fichier doit être une grille valide de Nonogramme (fichier .sgriddler).
    """
    grille_depart='grille_de_jeu.txt' #grille non résolue
    marge_rows = marge(dico, 'rows')
    marge_cols = marge(dico, 'cols')
    rows = liste_valeur(dico, 'rows')
    cols = liste_valeur(dico, 'cols')
    coord = coordInput[1]
    carac = coordInput[0]
    
    with open(grille_depart, 'rt',encoding='UTF-8') as entree:
        liste = entree.readlines()
            
    chaine = liste[coord[0]+marge_cols]
    chaine = chaine.split()
    res=''
            
    longueurMarge = len(chaine) - len(cols)
    chaine[coord[1]+longueurMarge] = carac
    res = ' ' * 3 * (marge_rows - longueurMarge)

    i = 0
    while i < len(chaine):
        res += espacement(chaine[i])
        i+=1
    res += ' \n'

    liste[coord[0]+marge_cols] = res

    with open(grille_depart, 'wt',encoding='UTF-8') as sortie:  #Si une erreur est detecté,la grille active reste la dernière valide.
        sortie.write(''.join(liste))

    if impr:
        impression(liste,marge_cols,marge_rows,cols)
        
def coord_graphique(liste, cle, marge):
    """
    Place les indications du nonogramme dans les cases appropriées dans la grille de jeu a la souris.
    :param liste: (liste) La liste de liste de valeurs des indications du nonogramme.
    :param cle: (str) La cle a calculer ('rows') ou ('cols').
    :param marge: (int) La marge la plus haute.
    :return: (none)
    CU: Aucune
    """
    k=0
    while k < len(liste):
        if cle == 'rows':
            i = marge - 1
            while i >= 0:
                if len(liste[k]) > i:
                    appcat.setrows(k,marge - 1 - i,liste[k][i], 'black')
                i -= 1
                
        elif cle == 'cols':
            i = marge - 1
            while i >= 0:
                if len(liste[k]) > i:
                    appcat.setcols(marge - 1 - i,k,liste[k][i], 'black')
                i -= 1
        k += 1
        
################################## Fonctions spécifique au jeu à la souris en nonogramme coloré. ##################################
def coord_graphique_color(liste, cle, marge):
    """
    Place les indications du nonogramme dans les cases appropriées dans la grille de jeu a la souris avec les couleurs associées.
    :param liste: (liste) La liste de liste de valeurs des indications du nonogramme.
    :param cle: (str) La cle a calculer ('rows') ou ('cols').
    :param marge: (int) La marge la plus haute.
    :return: (none)
    CU: Aucune.
    """
    k=0
    while k < len(liste):
        if cle == 'rows':
            i = marge - 1
            while i >= 0:
                if len(liste[k]) > i:
                    tempo = liste[k][i].split('#')
                    appcat.setrows(k,marge - 1 - i,tempo[0], dictColor['#'+tempo[1]])
                i -= 1
                
        elif cle == 'cols':
            i = marge - 1
            while i >= 0:
                if len(liste[k]) > i:
                    tempo = liste[k][i].split('#')
                    appcat.setcols(marge - 1 - i,k,tempo[0], dictColor['#'+tempo[1]])
                i -= 1
                
        k += 1

def SupprimeCroixVictoire_Clavier(liste):
    """
    Remplace tout les 'X' d'une liste par des '.'.
    :param liste: La liste de la grille de jeu active gagné.
    :return: (none)
    CU: Aucune.
    """
    x=0
    while x < len(liste):
        if liste[x] == 'X':
            liste[x] = '.'
        x+=1

def SupprimeCroixVictoire_Souris(self,liste):
    """
    Retire toute les croix de la fenêtre de jeu en cas de victoire.
    :param self: La fenêtre de jeu.
    :param liste: La liste de la grille de jeu active gagné.
    :return: (none)
    CU: Aucune.
    """
    x=0
    while x < len(liste):
        if liste[x] == 'X':
            self.emptybox(x//len(cols),x%len(cols))             #x//len(cols) => coordonnées en X       x%len(cols) => coordonnées en Y
        x+=1
        
########## #Verifie si la grille est correctement complété et est fonctionnel dans le mode noir et blanc. ##########
def cocheO(x,y):
    """
    Rempli la case coché en noir dans le tkinter nonogramme, et place un O dans le fichier grille_de_jeu.txt, (clic droit).
    :param x: (int) Coordonnée de la case selon l'axe x.
    :param y: (int) Coordonnée de la case selon l'axe y.
    :return: (none)
    CU: Aucune.
    """
    appcat.fillbox(x,y,'black')
    edit_grille(dico,['O',(x,y)],False) #Paramètre False car on ne souhaite pas l'impression de la grille dans le graphique.
    joueur = determination_resolution_joueur()
    if solution == joueur:
        appcat.fillbox(x,y,'black')
        SupprimeCroixVictoire_Souris(appcat,joueur)
        appcat.victoire = tk.Label(appcat, text='Bien joué ! Vous avez résolu le Nonogramme.')
        appcat.time = tk.Label(appcat, text='La fenêtre va se fermer dans une dizaine de secondes.')
        appcat.victoire.grid()
        appcat.time.grid()
        appcat.after(10000,appcat.quitanddestroy) #Ferme la fenêtre de jeu au bout de 10 secondes.
                
def cocheX(x,y):
    """
    Rempli la case coché d'une crois dans le tkinter nonogramme, et place un X dans le fichier grille_de_jeu.txt, (clic gauche).
    :param x: (int) Coordonnée de la case selon l'axe x.
    :param y: (int) Coordonnée de la case selon l'axe y.
    :return: (none)
    CU: Aucune.
    """
    appcat.checkbox(x,y)
    edit_grille(dico,['X',(x,y)],False) #Paramètre False car on ne souhaite pas l'impression du repère dans le graphique.
    joueur = determination_resolution_joueur()
    if solution == joueur:
        appcat.checkbox(x,y)
        appcat.victoire = tk.Label(appcat, text='Bien joué ! Vous avez résolu le Nonogramme.')
        appcat.time = tk.Label(appcat, text='La fenêtre va se fermer dans une dizaine de secondes.')
        appcat.victoire.grid()
        appcat.time.grid()
        appcat.after(10000,appcat.quitanddestroy) #Ferme la fenêtre de jeu au bout de 10 secondes.

def effaceCoche(x,y):
    """
    Remet une case à l'état vide dans le tkinter nonogramme, et place un . dans le fichier grille_de_jeu.txt, (clic de molette).
    :param x: (int) Coordonnée de la case selon l'axe x.
    :param y: (int) Coordonnée de la case selon l'axe y.
    :return: (none)
    CU: Aucune.
    """
    appcat.emptybox(x,y)
    edit_grille(dico,['.',(x,y)],False) #Paramètre False car on ne souhaite pas l'impression du repère dans le graphique.
    joueur = determination_resolution_joueur()
    if solution == joueur:
        appcat.emptybox(x,y)
        appcat.victoire = tk.Label(appcat, text='Bien joué ! Vous avez résolu le Nonogramme.')
        appcat.time = tk.Label(appcat, text='La fenêtre va se fermer dans une dizaine de secondes.')
        appcat.victoire.grid()
        appcat.time.grid()
        appcat.after(10000,appcat.quitanddestroy) #Ferme la fenêtre de jeu au bout de 10 secondes.

########## #Verifie si la grille est correctement complété et gère les couleurs aux clics. ##########
def cocheColor(x,y):
    """
    Rempli la case coché avec la couleur choisi dans le tkinter nonogramme, et place un O dans le fichier grille_de_jeu.txt.
    :param x: (int) Coordonnée de la case selon l'axe x.
    :param y: (int) Coordonnée de la case selon l'axe y.
    :return: (none)
    CU: Aucune.
    """
    appcat.emptybox(x,y)
    appcat.fillbox(x,y,dictColor[var.get()])
    edit_grille_color([var.get(),(x,y)])
    joueur = determination_resolution_joueur_color()
    if solution == joueur:
        appcat.fillbox(x,y,dictColor[var.get()])
        SupprimeCroixVictoire_Souris(appcat,joueur)
        appcat.victoire = tk.Label(appcat, text='Bien joué ! Vous avez résolu le Nonogramme.')
        appcat.time = tk.Label(appcat, text='La fenêtre va se fermer dans une dizaine de secondes.')
        appcat.victoire.grid()
        appcat.time.grid()
        appcat.after(10000,appcat.quitanddestroy) #Ferme la fenêtre de jeu au bout de 10 secondes.
                
def cocheXColor(x,y):
    """
    Rempli la case coché d'une crois dans le tkinter nonogramme, et place un X dans le fichier grille_de_jeu.txt, (clic gauche).
    :param x: (int) Coordonnée de la case selon l'axe x.
    :param y: (int) Coordonnée de la case selon l'axe y.
    :return: (none)
    CU: Aucune.
    """
    appcat.checkbox(x,y)
    edit_grille_color(['X',(x,y)])
    joueur = determination_resolution_joueur_color()
    if solution == joueur:
        appcat.checkbox(x,y)
        appcat.victoire = tk.Label(appcat, text='Bien joué ! Vous avez résolu le Nonogramme.')
        appcat.time = tk.Label(appcat, text='La fenêtre va se fermer dans une dizaine de secondes.')
        appcat.victoire.grid()
        appcat.time.grid()
        appcat.after(10000,appcat.quitanddestroy) #Ferme la fenêtre de jeu au bout de 10 secondes.

def effaceCocheColor(x,y):
    """
    Remet une case à l'état vide dans le tkinter nonogramme, et place un . dans le fichier grille_de_jeu.txt, (clic de molette).
    :param x: (int) Coordonnée de la case selon l'axe x.
    :param y: (int) Coordonnée de la case selon l'axe y.
    :return: (none)
    CU: Aucune.
    """
    appcat.emptybox(x,y)
    edit_grille_color(['.',(x,y)])
    joueur = determination_resolution_joueur_color()
    if solution == joueur:
        appcat.emptybox(x,y)
        appcat.victoire = tk.Label(appcat, text='Bien joué ! Vous avez résolu le Nonogramme.')
        appcat.time = tk.Label(appcat, text='La fenêtre va se fermer dans une dizaine de secondes.')
        appcat.victoire.grid()
        appcat.time.grid()
        appcat.after(10000,appcat.quitanddestroy) #Ferme la fenêtre de jeu au bout de 10 secondes.
                
########## Utilisation sur le terminal + Utilisation sur tkinter ##########
if __name__ == '__main__':
    #Méchanisme de sélection du nonogramme et du mode sur le terminal.
    print('Bienvenue sur le jeu du Nonogramme, voici une petite information avant de commencer :')
    if sys.platform=='win32' or sys.platform=='cygwin' or sys.platform=='darwin': #Si c'est un système  Windows ou Mac OS on indique que c'est ctrl-C.
        print("En mode clavier vous pourrez quitter le jeu en combinant les touches 'Ctrl-C'.\nEt en mode souris en cliquant sur le bouton quitter.\nBon jeu!")
    else:              #Si c'est un système Linux on indique que c'est ctrl-D.
        print("En mode clavier vous pourrez quitter le jeu en combinant les touches 'Ctrl-D'.\nEt en mode souris en cliquant sur le bouton quitter.\nBon jeu!")
        
    jeu = input("Tapez:\n\t'1' pour avoir les règles du jeu.\n\tSinon appuyez sur 'entrée' pour jouer directement.\n")
    if jeu == '1':
         modeJeu()
    
    test = input("Mode clavier ou mode souris ? \nTapez:\n\t'1' pour jouer au clavier.\n\t'2' pour jouer à la souris.\n\t").lower()
    while (test != '1') and (test !='2'):
        test = input("Mode clavier ou mode souris ? \nTapez:\n\t'1' pour jouer au clavier.\n\t'2' pour jouer à la souris.\n\t").lower()

    if test == '2':#Si l'on joue en mode graphique (tkinter) avec la souris.
        souris = input("Tapez: '1' Pour connaitre le fonctionnement du mode 'souris'\nAppuyez simplement sur entrée ,pour le mode 'souris'\n")
        
        if souris == '1':
            modeSouris()
            
        mode_color = ''
        while (mode_color != '1') and (mode_color !='2'):
            mode_color = input("\nMode noir et blanc ou mode coloré ?\nTapez:\n\t'1' pour le mode noir et blanc.\n\t'2' pour le mode coloré. \n\t").lower()
###### CHOIX : JOUER EN MODE NOIR ET BLANC #####
        if mode_color == '1':                       #Dans ce cas on joue aux fichier .sgriddler de l'archive gs14.zip de Juraj Simlovic.
            chemin = choix_dossier(choix_dossier('./puzzles'))
            dico = creation_dico(chemin)
            marge_rows = marge(dico, 'rows')
            marge_cols = marge(dico, 'cols')
            rows = liste_valeur(dico, 'rows')
            cols = liste_valeur(dico, 'cols')
            appcat=NonogramApplication(width=len(cols), height=len(rows), colsheight=marge_cols, rowswidth=marge_rows)
            coord_graphique(rows, 'rows', marge_rows)
            coord_graphique(cols, 'cols', marge_cols)

            grille('grille_de_jeu.txt',dico,False)
            
            solution = determination_solution_sgriddler(chemin)
            joueur = determination_resolution_joueur()
                
            appcat.appele(1, cocheO)
            appcat.appele(3, cocheX)
            appcat.appele(2, effaceCoche)
            appcat.mainloop()
###### CHOIX : JOUER EN MODE COULEUR #####
        elif mode_color == '2':                      #Dans ce cas on joue aux .sgriddler que nous avons crée et qui sont coloré.
            chemin = choix_dossier('./puzzlesColor')
            dico = creation_dico_color(chemin)
            marge_rows = marge(dico, 'rows')
            marge_cols = marge(dico, 'cols')
            rows = liste_valeur(dico, 'rows')
            cols = liste_valeur(dico, 'cols')
            appcat = NonogramApplication(width=len(cols), height=len(rows), colsheight=marge_cols, rowswidth=marge_rows)
            coord_graphique_color(rows, 'rows', marge_rows)
            coord_graphique_color(cols, 'cols', marge_cols)
            
            grille_color('grille_de_jeu.txt',dico)

            solution = determination_solution_sgriddler_color(chemin)
            joueur = determination_resolution_joueur_color()

            listeColor = determinate_color(chemin)

            var = StringVar()
            var.set(listeColor[0])

            i=0
            while i < len(listeColor):
                buttonColor = tk.Radiobutton(appcat, background= dictColor[listeColor[i]],value=listeColor[i],variable=var)
                buttonColor.grid(row=2,column=i+1)
                i+=1
                
            appcat.appele(1, cocheColor)
            appcat.appele(3, cocheXColor)
            appcat.appele(2, effaceCocheColor)
            appcat.mainloop()
###### CHOIX : JOUER EN MODE TEXTE SUR LE TERMINAL #####
    elif test == '1':                                     #Sinon on joue sur le terminal en mode texte et au clavier
        clavier = input("Tapez :\n\t'1': Fonctionnement du mode 'clavier'.\n\tAppuyez simplement sur entrée ,pour le mode 'clavier.'\n")
        if clavier == '1':
            modeClavier()
            
        chemin = choix_dossier(choix_dossier('./puzzles'))
        solution = determination_solution_sgriddler(chemin)
        
        dico = creation_dico(chemin)
        longCols = len(liste_valeur(dico, 'cols'))
        grille('grille_de_jeu.txt',dico,True)            #Paramètre True car on souhaite l'impression du repère dans le terminal.
        joueur = determination_resolution_joueur()
        
        while solution != joueur:
            coord = entreeCoord(dico)
            edit_grille(dico,coord,True)
            joueur = determination_resolution_joueur()    #Verifie si la grille est correctement complété en mode terminal.

        print('-' * 22+'\n\tVICTOIRE\n'+'-'*22)
        SupprimeCroixVictoire_Clavier(joueur) #Enlève les croix pour mieux voir le dessin en cas de victoire.
        i=0
        while i < len(joueur):
            j=0
            while j < longCols:
                print(espacement(joueur[i+j]), end='')
                j+=1
            print('\n', end='')
            i+=j
        print('\nBien joué ! Vous avez résolu le nonograme.')