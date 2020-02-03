###################################
### Sous-fonctions du nonogramme###
###################################
#Bonvoisin Alexandre
#Phu Valentin 
#Valet Tristan

import os

def determination_valeurs(liste):
    """
    Retourne une chaine de caractère contenant les numéros des coordonnées séparés par des ','.
    :param liste: (list) Liste de coordonnées au format [ n1,n2 ].
    :return: (str) Chaine de caractère des coordonnées.
    CU: Aucune.
    Exemple:
    >>> determination_valeurs(['2,3,4,5,'])
    '2,3,4,5'
    >>> determination_valeurs([' 2,'])
    '2'
    >>> determination_valeurs(['\\n ,'])
    ''
    """
    i=0
    while i < len(liste):
        liste[i] = liste[i].split()
        if len(liste[i]) == 0:
            liste[i] += ['0']
        i+=1
    final = ''

    i=0
    while i < len(liste):
        j=0
        while j < len(liste[i]):
            final += liste[i][j]
            j += 1
            if not j >= len(liste[i]):
                final += ','
        if len(liste[i]) == 0:
            final += '0'
        i+=1
        if not i >= len(liste):
            final += ';'
        if final[-1] == ',':
            final = final[:-1]
    return final

def creation_dico(fichier):
    """
    Retourne le dictionnaire contenant les coordonnées, au sein de 2 cles : rows et cols.
    :param fichier: (str) Nom du fichier que l'on veut ouvrir.
    :return: (dict) Dictionnaire contenant les lignes et les colonnes.
    CU:Le fichier doit être une grille valide de Nonogramme (fichier .sgriddler).
    """
    with open(fichier, 'rt', encoding = 'UTF-8') as entree:
        liste = entree.readlines()

    liste_temp = liste[2].split()
    rows = int(liste_temp[1])
    cols = int(liste_temp[0])

    liste_valeur = liste[rows + 5:]         #liste contenant les valeurs des lignes et colonnes
    liste_cols = liste_valeur[0:cols]       #liste des valeurs des colonnes
    liste_rows = liste_valeur[cols + 1:-1]  #liste des valeurs des lignes
    
    dico = {'rows': determination_valeurs(liste_rows),
            'cols': determination_valeurs(liste_cols)}
    return dico

def marge(dico, cle):
    """
    Retourne la plus grande marge (nombre max de chiffre) que les lignes ou les colonnes peuvent contenir en fonction du document
    :param dico: (dict) Dictionnaire contenant les lignes et les colonnes.
    :param cle: (str) La cle a calculer.
    :return: (int) La marge la plus haute.
    CU: cle == 'rows' or cle == 'cols' , dico provenant de la fonction creation_dico.
    """
    assert cle == 'rows' or cle == 'cols', 'mauvais parametre pour cle'
    res=1
    for i in dico[cle].split(';'):
        if res < i.count(',')+1:
            res = i.count(',')+1 #Compte le nombre de ',' pour faire la marge
                                 #si un précédant compteur plus petit existe, il est remplacé.
    return res


def liste_valeur(dico, cle):
    """
    Retourne une liste de liste contenant les valeurs, des lignes ou des colonnes.
    :param dico: (dict) Dictionnaire contenant les colonnes et les lignes.
    :param cle: (str) La cle utilisé dans le dico provenant de la fonction creation_dico.
    :return: (list) Une liste de liste de valeur des indications du nonogramme.
    CU: cle == 'rows' or cle == 'cols'.
    """
    assert cle == 'rows' or cle == 'cols', "mauvais paramètre pour cle ; les clés sont 'rows' et 'cols'"

    liste = []
    for k in dico[cle].split(';'):
        liste += [k.split(',')]    #Décompose le dico en une liste de valeur de colonne ou de ligne.
    return liste

def determination_solution_sgriddler(chemin):
    """
    Retourne une liste de la forme finale du nonogramme.
    :param chemin:(str) Nom du chemin du fichier Nonogramme valide.
    :return: (list) Forme valide du nonogramme.
    CU: Le fichier doit être une grille valide de Nonogramme (fichier .sgriddler).
    Exemple:
    >>> determination_solution_sgriddler('../puzzles/01 starters/nipper.sgriddler') 
    ['X', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'X',
    'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O',
    'O', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O',
    'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'X', 'X',
    'X', 'X', 'O', 'X', 'O', 'O', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'X', 'X', 'X', 
    'O', 'O', 'O', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 
    'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'O', 'X', 
    'X', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 
    'X', 'X', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'O', 
    'O', 'X', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'X', 
    'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'X', 
    'O', 'X', 'X', 'X', 'X', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 
    'X', 'X', 'X', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'X', 'O', 'O', 'X', 
    'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 
    'O', 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'X', 
    'X', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'X', 
    'X', 'X', 'O', 'X', 'O', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'O', 'X', 
    'O', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 
    'O', 'X', 'O', 'X', 'O', 'X', 'O', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X', 'X', 
    'X', 'X', 'X', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'X', 'X', 'X', 'O', 
    'O', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 
    'X', 'X', 'X', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'X', 
    'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 
    'X', 'O', 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O',
    'O', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O']
    """
    with open(chemin, 'rt', encoding = 'UTF-8') as entree:
        liste = entree.readlines()

    liste_temp = liste[2].split(' ')
    rows = int(liste_temp[1])
    grille = liste[4:4+rows]
    i = 0
    liste = []
    while i < len(grille):
        for j in grille[i]:
            if j == '#':
                liste += 'O'
            elif j == '.':
                liste += 'X'
        i += 1
    return liste

def determination_resolution_joueur():
    """
    Retourne une liste de la forme active de la grille de jeu.
    :return: (list) Forme active du nonogramme.
    CU: Aucune.
    """
    with open('grille_de_jeu.txt', 'rt', encoding = 'UTF-8') as entree:
        liste = entree.readlines()
        
    variable = ['.','O','X']
    
    liste_tempo = []
    trouve = False
    liste_val_active =[]
    i=0

    while not trouve:
        if variable[0] in liste[i] or variable[1] in liste[i] or variable[2] in liste[i]:
            trouve = True
            nb_cols = len(liste[i-1].split())
            liste = liste[i:]
        i+=1

    for i in liste:
        liste_tempo += [i.split()[-nb_cols:]]

    for i in liste_tempo:
        for j in i:
            if j == 'X' or j == '.':
                liste_val_active += ['X']
            elif j == 'O':
                liste_val_active += ['O']
    return liste_val_active

def choix_dossier(chemin):
    """
    Affiche les sous-dossiers du chemin en parametre et renvoie le chemin avec le choix de l'utilisateur
    :param chemin: (str) Chaine de caractere du chemin choisi.
    :return: (str) Le nouveau chemin.
    CU: Le chemin doit exister, input = une valeur choisi.
    """
    liste = os.listdir(chemin)
    liste.sort()
    affiche = ''
    tempo = ''
    
    for i in range(len(liste)):
        if liste[i][0] == 0:
            tempo = str(i + 1) + ' - ' + liste[i]
        else:
            tempo = str(i + 1) + ' - ' + liste[i].split('.')[0]
        if (len(tempo) > 15):
            affiche += tempo + '\t'
        elif (len(tempo) > 7):
            affiche += tempo + '\t\t'
        else:
            affiche += tempo + '\t\t\t'
        
        if i%3 == 2:
            affiche += '\n'
            
    print(affiche)
    Int_val=False #Variable permettant de boucler le try
    res_str = input('Quelle est votre choix ? (indiquez le nombre) ')
    while Int_val !=True:
        try: # On essaye de convertir le resultat en entier
            int(res_str) #Test si le input peut être converti en nombre, sinon erreur.
            res=int(res_str)-1
            return chemin + '/' + liste[res] #Quand le return se produit, alors le try est valide et donc la boucle s'arrete. 
        except:
            print('Mauvais indice')
            res_str = input('Quelle est votre choix ? (indiquez le nombre) ')
            
######################## Repère ##############################
#Repère en ordonne:
def ordonnes(cmpt,marge):
    """
    Affiche sur la droite, le repère en ordonné de la grille.
    :param cpmt: (int) Un compteur démarrant a 0.
    :param marge: (int) La marge pour placer le repère par rapport a la grille.
    :return: (none)
    CU: Aucune.
    """
    if len(str(cmpt)) == 1:
        print('  |  ' + str(cmpt-marge +1))
    else:
        print('  | ' + str(cmpt-marge+1))
    
#Repère en abscisse :
def abscisses(marge,col):
    """
    Affiche en bas, le repère en abscisse de la grille.
    :param marge: (int) La marge pour placer le repère par rapport a la grille.
    :param col: (list) Liste contenant les valeurs des colonnes et le donc le nombre de colonne au maximum.
    :return: (none)
    CU: Aucune.
    """
    print(' ' * 3 * marge + '  _'*(len(col)) + '     ^y')
    rabscisse=1
    print('   ' * marge,end="") #end='' -> pour que le print ne se termine pas par un '\n' et ici on mets l'espace de la marge
    while rabscisse <= len(col):
        if rabscisse<=9:
            print('  '+str(rabscisse),end='')
        else:
            print(' '+str(rabscisse),end='')
        rabscisse+=1
    print(' <x  Repère')
######################## Fin Repère ##############################

def espacement(caractere):
    """
    Retourne le caractère paramétré avec un certain nombre d'espace le précédent, pour fabriquer la grille.
    :param caractere: (str) Le caractère dont on doit determiner l'espacement.
    :return: (str) Le caractère précédé d'espaces.
    CU: Aucune.
    Exemple:
    >>> espacement('1')
    '  1'
    >>> espacement('10')
    ' 10'
    """
    car= str(caractere)
    if len(car) == 1:       #Ajout d'un espace si c'est un numéro entre [0:9]
        res = '  ' + car
    else:
        res = ' ' + car
    return res

def impression(liste,marge_cols,marge_rows,cols):
    """
    Imprime le repère si l'on joue sur le terminal.
    :param liste: (list) Des lignes en lecture.
    :param marge_cols: (int) Marge des colonne.
    :param marge_rows: (int) Marge des lignes.
    :param cols: (list) Liste des valeurs des colonnes.
    :return: (none)
    CU: Aucune.
    """
    for i in range(len(liste)):
        if i>=marge_cols:
            print(liste[i][:-2],end="")  #Imprime le repère en ordonnées
            ordonnes(i,marge_cols)
        else:
            print(liste[i],end="")
    abscisses(marge_rows,cols)
    
################################## Fonctions spécifique au jeu à la souris en nonogramme coloré. ##################################
def determination_solution_sgriddler_color(chemin):
    """
    Retourne une liste de la forme finale d'un nonogramme coloré.
    :param chemin:(str) Nom du chemin du fichier Nonogramme coloré.
    :return: (list) Forme valide du nonogramme.
    CU: Le fichier doit être une grille valide de Nonogramme coloré (fichier .sgriddler).
    Exemple:
    >>> determination_solution_sgriddler('../puzzlesColor/goomba.sgriddler')
    ['X', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X',
    'X', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X',
    'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O',
    'O', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O',
    'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O',
    'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O',
    'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O',
    'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X',
    'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
    'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
    'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
    'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
    'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'X', 'O', 'O',
    'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O',
    'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'O',
    'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X',
    'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O', 'O', 'O',
    'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O',
    'O', 'O', 'X', 'X']
    """
    with open(chemin, 'rt', encoding = 'UTF-8') as entree:
        liste = entree.readlines()

    liste_temp = liste[2].split()
    rows = int(liste_temp[1])
    grille = liste[4:4+rows]
    i = 0
    tempo = '' 
    listeRes = []
    while i < len(grille):
        j = 0
        while j < len(grille[i]):
            if grille[i][j] == '.':
                listeRes += ['X']
            elif grille[i][j] == '#':
                tempo = grille[i][j:j+2]
                listeRes += [tempo]
                j += 1
            j += 1
        i += 1
    return listeRes

def determination_resolution_joueur_color():
    """
    Retourne une liste de la forme active de la grille de jeu coloré.
    :return: (list) Forme active du nonogramme coloré.
    CU: Aucune.
    """
    with open('grille_de_jeu.txt', 'rt', encoding = 'UTF-8') as entree:
        liste = entree.readlines()

    i = 0
    listeRes = []
    tempo = ''
    while i < len(liste):
        j = 0
        while j < len(liste[i]):
            if liste[i][j] == '.' or liste[i][j] == 'X':
                listeRes += ['X']
            elif liste[i][j] == '#':
                tempo = liste[i][j:j+2]
                listeRes += [tempo]
                j += 1
            j += 1
        i += 1
    return listeRes

def determinate_color(fichier):
    """
    Retourne la liste des couleurs présente sur le nonogramme.
    :fichier: (str) Nom du nonogramme coloré.
    :return: (list) Liste des couleurs présente sur le nonogramme.
    CU: Aucune.
    Exemple:
    >>> determinate_color('../puzzlesColor/goomba.sgriddler')
    ['#4', '#7', '#F']
    """
    with open(fichier, 'rt',encoding='UTF-8') as entree:
        liste = entree.readlines()
    return liste[2].split()[3:]

def grille_color(fichier,dico):
    """
    Ecris dans le fichier la grille du niveau coloré.
    :param fichier: (str) Chaine de caractère precisant le nom du fichier.
    :param dico: (dict) Dictionnaire contenant les colonnes et les lignes du modèle de nonogramme choisi.
    :return: (None)
    CU: Aucune.
    """
    rows = liste_valeur(dico, 'rows')
    cols = liste_valeur(dico, 'cols')

    with open(fichier, 'wt', encoding='UTF-8') as sortie:
        for i in range(len(rows)):
            sortie.write('  .'*len(cols)+'\n')

def edit_grille_color(coordInput):
    """
    Edite la grille en fonction des clics du joueur sur une grille coloré.
    :param coordInput:(list) Coordonnées du clic du joueur.
    :return:(None)
    CU: La grille de jeu est une grille coloré.
    """
    grille_depart='grille_de_jeu.txt'
    
    with open(grille_depart, 'rt',encoding='UTF-8') as entree:
        liste = entree.readlines()
    
    liste_tempo = liste[coordInput[1][0]].split()
    liste_tempo[coordInput[1][1]] = coordInput[0]
    res=''
    for i in liste_tempo:
        res += espacement(i)
    liste[coordInput[1][0]] = res + '\n'

    with open(grille_depart, 'wt',encoding='UTF-8') as sortie:
        for i in liste:
            sortie.write(i)

def creation_dico_color(fichier):
    """
    Retourne le dictionnaire contenant les coordonnées, au sein de 2 cles : 'rows' et 'cols'.
    :param fichier: (str) Nom du fichier que l'on veut ouvrir.
    CU: Le fichier doit être une grille valide de Nonogramme (fichier .sgriddler).
    """
    with open(fichier, 'rt', encoding = 'UTF-8') as entree:
        liste = entree.readlines()

    liste_temp = liste[2].split()
    rows = int(liste_temp[1])
    cols = int(liste_temp[0])

    liste_valeur = liste[rows + 5:]         #liste contenant les valeurs des lignes et colonnes
    liste_cols = liste_valeur[0:cols]       #liste des valeurs des colonnes
    liste_rows = liste_valeur[cols + 1:]    #liste des valeurs des lignes
    
    dico = {'rows': determination_valeurs(liste_rows),
            'cols': determination_valeurs(liste_cols)}
    return dico

######### Fonctions pour le menu sur le terminal. #########
def modeJeu():
    """
    Permet d'afficher les règles du jeu si le joueur le souhaite.
    :return: (none)
    CU: Aucune.
    """
    with open("manuel-utilisation.txt",'rt',encoding='UTF-8') as entree:
        liste = entree.readlines()
    listeModeJeu = liste[19:27]
    str = ''
    for i in listeModeJeu:
        if i != '\n':
            str += i
    print(str)
    
def modeClavier():
    """
    Permet d'afficher le fonctionnement du mode clavier.
    :return: (none)
    CU: Aucune.
    """
    with open("manuel-utilisation.txt",'rt',encoding='UTF-8') as entree:
        liste = entree.readlines()
    listeModeClavier = liste[35:40]
    str = ''
    for i in listeModeClavier:
        if i != '\n':
            str += i
    print(str)
           
def modeSouris():
    """
    Permet d'afficher le fonctionnement du mode souris.
    :return: (none)
    CU: Aucune.
    """
    with open("manuel-utilisation.txt",'rt',encoding='UTF-8') as entree:
        liste = entree.readlines()
    listeModeSouris = liste[46:51]
    str = ''
    for i in listeModeSouris:
        if i != '\n':
            str += i
    print(str)
           
###############################################################################################
if __name__ == '__main__':
    import doctest
    doctest.testmod(optionflags=doctest.NORMALIZE_WHITESPACE)