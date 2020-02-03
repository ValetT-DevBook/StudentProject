#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""
:mod: tk_nonogram

:author: Jean-Luc Levaire

:date: 2018, february

Interface graphique pour le projet du jeu de nonogram.
"""


import tkinter as tk
#Besoin d'importer cette variable définit dans le module tkinter pour la gestion des couleurs par Radiobutton :
from tkinter import StringVar

class CGrid:
    MAX_BUTTON = 7
    def __init__(self, canvas, width=20, height=20, borderwidth=1, hmargin=1, vmargin=1,
                 hpad=1, vpad=1, hbox=20, vbox=20, xpos=0, ypos=0, bg=None, clickable=False):
        self.canvas=canvas
        self.width=width
        self.height=height
        self.borderwidth=borderwidth
        self.hmargin=hmargin
        self.vmargin=vmargin
        self.hpad=hpad
        self.vpad=vpad
        self.hbox=hbox
        self.vbox=vbox
        self.xpos=xpos
        self.ypos=ypos
        self.rects_by_coords=[]
        if bg:
            self.bg=bg
        else:
            self.bg=self.canvas.cget('bg')
        self.callbacks=[None for i in range(CGrid.MAX_BUTTON)]
        self.clickable=clickable
        if self.clickable:
            self.canvas.bind('<Button>', self.callback_button)
        self.itemgrid=[[list() for j in range(self.width)] for i in range(self.height)]
        
    def draw(self):
        width, height = self.size()
        self.canvas.config(width=width, height=height)
        activeoutline=None
        if self.clickable:
            activeoutline='white'
        y = 0
        for l in range(self.height):
            x = 0
            lrects_by_coords=[]
            for c in range(self.width):
                rect=self.canvas.create_rectangle(x + self.hmargin, y + self.vmargin,
                                                  x + self.hmargin + self.hbox, y + self.vmargin + self.vbox,
                                                  activeoutline=activeoutline, fill=self.bg)
                xcenter=x + self.hmargin + (self.hbox // 2)
                ycenter=y + self.vmargin + (self.vbox // 2)
                lrects_by_coords.append((rect, xcenter, ycenter))
                x = x + self.hbox + 2 * self.hmargin
            y = y + self.vbox + 2 * self.vmargin
            self.rects_by_coords.append(lrects_by_coords)

    def size(self):
        return (self.width * (self.hbox + 2 * self.hmargin), self.height * (self.vbox + 2 * self.vmargin)) 

    def find_bycoords(self, canvasx, canvasy):
        return (canvasy // (self.vbox + 2 * self.vmargin), canvasx // (self.hbox + 2 * self.hmargin))
    
    def callback_button(self, event):
        x, y=self.find_bycoords(event.x, event.y)
        buttonindex=event.num-1
        assert buttonindex < CGrid.MAX_BUTTON, "Unknown Button index "+str(buttonindex)
        if self.callbacks[buttonindex]:
            self.callbacks[buttonindex](x, y)
        else:
            print("Clicked button", event.num, "at ", x, y)

    def setcallback(self, buttonindex, callback):
        assert buttonindex < CGrid.MAX_BUTTON, "Unknown Button index "+str(buttonindex)
        self.callbacks[buttonindex]=callback

    def fillbox(self, x, y, color='black'):
        rect=self.rects_by_coords[x][y][0]
        self.canvas.itemconfig(rect, fill=color)

    def checkbox(self, x, y, color='black', width=2):
        rect, xcenter, ycenter = self.rects_by_coords[x][y]
        self.canvas.itemconfig(rect, fill='white')
        x0=xcenter - self.hbox // 2; x1=xcenter+self.hbox//2
        y0=ycenter - self.vbox // 2; y1=ycenter+self.vbox//2
        line1=self.canvas.create_line(x0, y0, x1, y1, fill=color, width=width)
        line2=self.canvas.create_line(x0, y1, x1, y0, fill=color, width=width)
        self.itemgrid[x][y].append(line1)
        self.itemgrid[x][y].append(line2)

    def emptybox(self, x, y):
        rect=self.rects_by_coords[x][y][0]
        self.canvas.itemconfig(rect, fill=self.bg)
        for item in self.itemgrid[x][y]:
            self.canvas.delete(item)
        self.canvas.update_idletasks()
        self.itemgrid[x][y]=list()

class CGridText(CGrid):
    def __init__(self, canvas, width=20, height=20, xpos=0, ypos=0):
        super().__init__(canvas, width=width, height=height, xpos=xpos,ypos=ypos)
        self.textgrid=[[(None, None) for j in range(self.width)] for i in range(self.height)]

    def settext(self, x, y, text,color):
        rect, xcenter, ycenter = self.rects_by_coords[x][y]
        prevtext, prevtextitem = self.textgrid[x][y]
        self.canvas.delete(prevtextitem)
        textitem=None
        if text is not None:
            textitem=self.canvas.create_text(xcenter, ycenter, text=text,fill=color)
        self.textgrid[x][y] = (text, textitem)
        
    
        
class NonogramApplication(tk.Frame):
    """
Réalise l'interface graphique pour le jeu du nonogram.
La configuration se réalise à la création de l'application, les paramètres étant:
:param: width (int) nombre de colonnes du nonogram
:param: height (int) nombre de lignes du nonogram
:param: colsheight (int) hauteur de la zone affichant les nombres pour les colonnes du nonogram
:param: rowswidth (int) largeur de la zone affichant les nombres pour lignes du nonogram
Par exemple pour le nonogram du chat,
appcat=NonogramApplication(width=5, height=5, colsheight=2, rowswidth=2)

Pour remplir les cases de la zone affichant les nombres pour les colonnes du nonogram,
la méthode setcols(x, y, valeur) remplit la case de coordonnées x,y de cette zone avec
le nombre valeur. De même setrows(x, y, valeur) pour les lignes. Par exemple pour le
nonogram du chat:
appcat.setcols(0, 4, 3)
appcat.setcols(1,0,3);appcat.setcols(1,1,3);appcat.setcols(1,2,5);appcat.setcols(1,3,4);appcat.setcols(1,4,1);
appcat.setrows(0, 0, 1);appcat.setrows(0, 1, 1);
appcat.setrows(1, 1, 3);appcat.setrows(2, 1, 5);appcat.setrows(3, 1, 4); appcat.setrows(4, 1, 5);

Le contenu des cases du nonogram lui-même est modifiable à l'aide des méthodes suivantes:
- app.fillbox(x, y, color) remplit la case de coordonnées x, y avec la couleur color
- app.checkbox(x, y) place une croix dans la case de coordonnées x, y
- app.emptybox(x, y) efface le contenu de la case de coordonnées x, y

La boucle principale du jeu est lancée avec la méthode mainloop, mais avant de lancer
cette boucle il est nécessaire de préciser les actions à réaliser lors des événements
(clavier ou souris pour une interface). Pour ce jeu seuls les clicks des boutons de la souris
dans la grille de jeu sont utilisés: la méthode appele permet de préciser quelle fonction appeler
quand un bouton de souris est pressé sur une des cases de la grille. Les coordonnées de la case
cliquée sont passées en paramètre à ces fonctions.
def fonction_bouton1(x, y):
    appcat.fillbox(x, y, 'black')
def fonction_bouton3(x, y):
    appcat.checkbox(x, y)
appcat.appelle(1, fonction_bouton1)
appcat.appelle(3, fonction_bouton3)
appcat.mainloop()
Enfin la méthode quitanddestroy termine l'application et ferme sa fenêtre parente.
Elle est appelée par le bouton Quit de l'application. 
    """
    def __init__(self, master=None, width=20, height=20, colsheight=None, rowswidth=None):
        tk.Frame.__init__(self, master)   
        self.grid(sticky=tk.N+tk.S+tk.E+tk.W)
        self.width=width
        self.height=height
        self.colsheight=colsheight if colsheight else  ((self.height + 1)// 2)
        self.rowswidth=rowswidth if rowswidth else ((self.width + 1) // 2)
        self.createWidgets()

    def setcols(self, x, y, value, color='black'):
        self.cols.settext(x, y, value,color)

    def setrows(self, x, y, value, color='black'):
        self.rows.settext(x, y, value,color)

    def fillbox(self, x, y, color='black'):
        self.nonogramgrid.fillbox(x, y, color=color)

    def checkbox(self, x, y):
        self.nonogramgrid.checkbox(x, y)

    def emptybox(self, x, y):
        self.nonogramgrid.emptybox(x, y)

    def appele(self, buttonnum, callback):
        self.nonogramgrid.setcallback(buttonnum-1, callback)
        
    def quitanddestroy(self):
        """
Termine l'application et détruit la fenêtre parente
:returns: None
        """
        self.quit()
        self.winfo_toplevel().destroy()

    def createWidgets(self):
        """
Crée les éléments graphiques pour afficher un nonogram
:returns: None
        """
        top=self.winfo_toplevel()
        top.rowconfigure(0, weight=1)
        top.columnconfigure(0, weight=1)
        self.rowconfigure(0, weight=1)
        self.columnconfigure(0, weight=1)
        self.nonogramFrame=tk.Frame(self)
        self.nonogramFrame.grid(sticky=tk.N+tk.S+tk.E+tk.W, padx=10, pady=10)
        self.upleftCanvas = tk.Canvas(self.nonogramFrame, width=self.width, height=self.height, bg='white')
        self.colCanvas = tk.Canvas(self.nonogramFrame)
        self.rowCanvas = tk.Canvas(self.nonogramFrame)
        self.nonogramCanvas = tk.Canvas(self.nonogramFrame)
        self.cols=CGridText(self.colCanvas, height=self.colsheight, width=self.width)
        self.rows=CGridText(self.rowCanvas, width=self.rowswidth, height=self.height)
        self.nonogramgrid=CGrid(self.nonogramCanvas, width=self.width, height=self.height, clickable=True)
        self.cols.draw()
        self.rows.draw()
        self.nonogramgrid.draw()
        self.upleftCanvas.grid(row=0,column=0, padx=10, pady=10)
        self.colCanvas.grid(row=0,column=1, padx=10, pady=10)
        self.rowCanvas.grid(row=1,column=0, padx=10, pady=10)
        self.nonogramCanvas.grid(row=1,column=1, padx=10, pady=10)
        self.quitButton = tk.Button(self, text='Quit',
            command=self.quitanddestroy)
        self.quitButton.grid()
        
        
########## Nous avons commenté cette partie car c'est notre projet.py qui gère la grille affiché sur tkinter. ##########
##if __name__=='__main__':    
##    appcat=NonogramApplication(width=5, height=5, colsheight=2, rowswidth=2)
##    appcat.master.title('Nonograms -- Le chat')
##    appcat.setcols(0, 4, 3)
##    appcat.setcols(1,0,3);appcat.setcols(1,1,3);appcat.setcols(1,2,5);appcat.setcols(1,3,4);appcat.setcols(1,4,1);
##    appcat.setrows(0, 0, 1);appcat.setrows(0, 1, 1);
##    appcat.setrows(1, 1, 3);appcat.setrows(2, 1, 5);appcat.setrows(3, 1, 4); appcat.setrows(4, 1, 5)
##    def fonction_bouton1(x, y):
##        appcat.fillbox(x, y, 'black')
##    def fonction_bouton3(x, y):
##        appcat.checkbox(x, y)
##    appcat.appele(1, fonction_bouton1)
##    appcat.appele(3, fonction_bouton3)    
##    appcat.mainloop()






