#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""
:mod:`Main Maze` module

:author: BONVOISIN Alexandre, PHU Valentin, VALET Tristan

:date: 2018, November

Provides 

This module provides functions to have a control terminal interface of the maze module.

"""
from maze import *
import sys, os, library, argparse

def usage():
    """
    Display an usage message.
    
    :return: None
    :side effect: print an usage message
    :UC: None
    """
    print('Usage : Please consult the help with the argument -h or --help of argparse \n\tOr consult the manual with the argument -m or --manual')
    exit()
    
def manual():
    """
    Display a complete manual of the terminal interface for the maze module.
    
    :return: None
    :side effect: print a complete manual
    :UC: None
    """
    with open('../manual/main_manual.txt','r',encoding='utf-8') as input:
        print(input.read())

def main_resolve(maze_element,arg):
    """
    Resolves the current maze if its possible, and display the solution if the user would.
    
    :param arg: an array of arguments
    :type arg: argparse.Namespace
    :param maze_element: a maze
    :type maze_element: maze.Maze
    :return: a resolution of the maze if its possible otherwise a maze error
    :rtype: maze.Maze
    :UC: None
    """
    if len(arg.resolve) == 0:
        maze_element.resolve()
        maze_element.show_solution()
    elif len(arg.resolve) == 2 :
        maze_element.resolve(arg.resolve[0],arg.resolve[1])
        maze_element.show_solution()
    else:
        maze_element.resolve(arg.resolve[0],arg.resolve[1],arg.resolve[2],arg.resolve[3])
        maze_element.show_solution()
    return maze_element

def main_random(arg):
    """
    Modify the current maze to a perfect maze by a random generation.
    
    :param arg: an array of arguments
    :type arg: argparse.Namespace
    :return: a perfect maze by a random generation.
    :rtype: maze.Maze
    :side effect: the current maze was modified into a perfect maze
    :UC: None
    """
    if len(arg.random) == 0:
        m = Maze()
        m.random()
    else:
        x,y = arg.random[0],arg.random[1]
        if x > 100 or y > 100:
            sys.setrecursionlimit(x*y)
        m = Maze(x, y)
        m.random()
    return m

def edit(arg):
    """
    Puts the user into an handmade edit mode of his maze.
    
    :param arg: an array of arguments
    :type arg: argparse.Namespace
    :return: the maze modified according to the expectations of the user
    :rtype: maze.Maze
    :UC: None
    """
    if arg.edit == []:
        m = Maze()
    else:
        m = Maze(arg.edit[0],arg.edit[1])
    m.set_state(MazeState.not_ready)
    
    width = m.get_width()
    height = m.get_height()
    ROW = 'row'
    ROOM = 'room'
    STOP = 'stop'
    
    while m.get_state() != MazeState.ready:
        print(m)
        maze_input = input('Do you change a row or a specific room of the maze ? (enter "row" / "room") \n To stop the edition enter "stop": ')

        if maze_input != '':
            if maze_input == ROW:
                try:
                    x = input('Please enter coord of the row \n\tx (number of the row)\n> ')
                    try:
                        x_position=int(x)
                    except:
                        raise print('\n'+'-'*80+'\nx must be an integer')
                    assert x_position<=m.get_height() and x_position>0,print('\n'+'-'*80+'\nx must be in the range [1:'+str(m.get_height())+']')
                    print('Please enter new pattern of the row with new bottom wall and new right wall \n'+\
                          '\t+ You can\'t open the door of the side of maze\n '+ \
                          '\t\t* if the room is on the right of maze, you must to close the right door\n' + \
                          '\t\t* if the room is on the bottom of maze, you must to close the bottom door\n' + \
                          '\t+ Separate each room with a comma and the exact number of room\n '+ \
                          '\t+ Don\'t forget the right closed door in the last room')
                    list_opened = input('For example: " |,- ,-|,  , |" (without apostrophes) \n> ').split(',')
                    assert list_opened[-1][-1]=='|',print('\n'+'-'*80+"\n'"+','.join(list_opened)+"' are bad arguments  : Need a | at the end")
                    m.change_row(x_position-1, list_opened)
                except:
                    print("\n Bad arguments")
            elif maze_input == ROOM:
                try:
                    x, y = input('Please enter coords \n\tx y (x and y coords of the room)\n> ').split(' ')
                    try:
                        pos_x=int(x)
                    except:
                        raise print('-'*80+'\nx must be an integer')
                    try:
                        pos_y=int(y)
                    except:
                        raise print('-'*80+'\ny must be an integer')
                    assert not pos_x>m.get_height() and pos_x>0,print('\n'+'-'*80+'\nx must be in the range [1:'+str(m.get_height())+']')
                    assert not pos_y>m.get_width()and pos_y>0,print('\ny must be in the range [1:'+str(m.get_width())+']')
                    print('Please enter new pattern of the room with new bottom wall and new right wall \n'+\
                          '\t+ You can\'t open the door of the side of maze\n '+ \
                          '\t\t* if the room is on the right of maze, you must to close the right door\n' + \
                          '\t\t* if the room is on the bottom of maze, you must to close the bottom door')
                    opened = input('For example: "-|" or "- " or " |" or "  " (without apostrophes) \n> ')
                    try:
                        m.change_room(pos_x-1, pos_y-1, opened)
                    except:
                        raise print('\n'+'-'*80+"\n'"+opened+"' is a bad arguments")
                except:
                    print("\nBad arguments")
            elif maze_input == STOP:
                m.end_edit()
            else:
                print('\n Bad arguments')
        else:
            print('\n Bad arguments')          
    return m


def main(arg):
    """
    Main function which transform user inputs into concrete event.
    
    :param arg: an array of arguments
    :type arg: argparse.Namespace
    :return: None
    :side effect: execute user inputs
    :UC: None
    """
    if arg.manual:
        manual()
        exit()
        
    elif arg.library:
        library.print_library('../maze_library/create')
        exit()
        
    elif arg.random != None or arg.edit != None or arg.file != None:
        if  (arg.random and arg.file) == None and arg.edit != None:
            if len(arg.edit) == 2 or len(arg.edit) == 0:
                m = edit(arg)
            else:
                print("The edit's mode needs 2 or 0 numbers for arguments.")
                exit()
                
        elif (arg.random and arg.edit) == None and arg.file != None:
            if len(arg.file) == 1:
                try:
                    if arg.file[0][-4:] != '.txt':
                        m = Maze.create_from_file('../maze_library/create/'+arg.file[0]+'.txt')
                    else:
                        m = Maze.create_from_file('../maze_library/create/'+arg.file[0])
                except:
                    print("The file's mode needs a valide file for arguments.")
                    print('Please, check the library with -l or -library.')
                    exit()
            else:
                print("The file's mode needs a valide file for arguments.")
                print('Please, check the library with -l or -library.')
                exit()
        
        elif (arg.edit and arg.file) == None and arg.random != None:
            if len(arg.random) == 2 or len(arg.random) == 0:                    
                m = main_random(arg)
            else:
                print("The edit's mode needs 2 or 0 numbers for arguments.")
                exit()
        
        if arg.save != None:
            if len(arg.save) == 1:
                if arg.save[0][-4:] != '.txt':
                    m.write_in_file('../maze_library/saves/'+arg.save[0]+'.txt')
                else:
                    m.write_in_file('../maze_library/saves/'+arg.save[0])
            else:
                print("The save's mode needs a valide file for arguments.")
                exit()
                        
        if arg.resolve != None:
            if len(arg.resolve) == 0 or len(arg.resolve) == 2 or len(arg.resolve) == 4 :
                m = main_resolve(m,arg)
            else:
                print("The resolve's mode needs 4 or 2 or 0 numbers for arguments.")
                exit()
        print(m)
        exit()
    usage()
          

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Help for maze's script")

    parser.add_argument('-m', '--manual', action="store_true", dest = 'manual', help = 'display the manual of the script')
    parser.add_argument('-l', '--library', action="store_true", dest = 'library', help = "display all maze available")
    parser.add_argument('-f', '--file', action="store", dest = 'file', nargs=1, type = str, help = "create a maze from a file")
    parser.add_argument('-r', '--random', action="store", dest = 'random', nargs='*', type = int, help = 'create a random maze (take 0 or 2 arguments)')
    parser.add_argument('-e', '--edit', action="store", dest = 'edit', nargs='*', type = int, help = 'create a maze with all doors closed and launch the edit of your own maze')
    parser.add_argument('-rs', '--resolve', action="store", dest = 'resolve', nargs='*', type = int, help = 'resolve the maze (take 0, 2 or 4 arguments)')
    parser.add_argument('-s', '--save', action="store", dest = 'save', nargs=1, type = str, help = 'save your maze')

    args = parser.parse_args()
    main(args)
