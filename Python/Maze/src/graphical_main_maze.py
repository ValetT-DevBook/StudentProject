#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""
:mod:`Graphical Main Maze` module

:author: BONVOISIN Alexandre, PHU Valentin, VALET Tristan

:date: 2018, November

Provides 

This module provides functions to have a graphical interface of the maze module.

"""
from tkinter import *
import maze, time, library, argparse, sys, os

#############################
#  Miscellaneous functions  #
#############################

def coordinate_center(x,y):
    """
    Return a tuple which contain coordinates of the center of the cliqued room.
    
    :param x: position in the x-axis
    :type x: int
    :param y: position in the y-axis
    :type y: int
    :return: coordinates of the center
    :rtype: tuple
    :UC: None
    """
    x = (x // DIMENSION_ROOM) * DIMENSION_ROOM
    y = (y // DIMENSION_ROOM) * DIMENSION_ROOM
    return (x + DIMENSION_ROOM//2, y + DIMENSION_ROOM//2)

def compare_coordinates_pac_man(x1, y1, x2, y2):
    """
    Compares 2 coordinates to know where the pacman will go.
    
    :param x1: current room's position in the x-axis of pacman
    :type x1: int
    :param y1: current room's position in the y-axis of pacman
    :type y1: int
    :param x2: next room's position in the x-axis
    :type x2: int
    :param y2: next room's position in the y-axis
    :type y2: int
    :return: the direction among top, bottom, left and right
    :rtype: str
    :UC: None
    """
    if x1 > x2:
        return 'LEFT'
    elif x1 < x2:
        return 'RIGHT'
    elif y1 > y2:
        return 'UP'
    elif y1 < y2:
        return 'DOWN'

def animation_one_room_pac_man(canvas, x, y, x_go, y_go):
    """
    Animates the crossing of the pacman between 2 rooms in 4 frames.
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :param x: current room's position in the x-axis of pacman
    :type x: int
    :param y: current room's position in the y-axis of pacman
    :type y: int
    :param x_go: next room's position in the x-axis
    :type x_go: int
    :param y_go: next room's position in the y-axis
    :type y_go: int
    :return: None
    :side effect: modifies the pacman circle between 2 rooms
    :UC: canvas must be an active Canvas
    """
    cycle_anim = ['OPEN','MIDDLE' ,'CLOSE', 'MIDDLE']
    direction = compare_coordinates_pac_man(x, y, x_go, y_go)    
    x = (x * DIMENSION_ROOM) + (DIMENSION_ROOM//2)
    y = (y * DIMENSION_ROOM) + (DIMENSION_ROOM//2)
    for i in range(4):
        erase_circle(canvas,'pac_man')
        draw_pac_man(canvas, x, y, direction, cycle_anim[i])
        time.sleep(6/100)
        if direction == 'UP':
            y -= DIMENSION_ROOM // 4
        elif direction == 'DOWN':
            y += DIMENSION_ROOM // 4
        elif direction == 'LEFT':
            x -= DIMENSION_ROOM // 4
        elif direction == 'RIGHT':
            x += DIMENSION_ROOM // 4


def binding_buttons(canvas, bool_pac_man, maze_element):
    """
    Allow user's clicks by binding the left and the right click of the mouse to actions. (left to resolve the maze, right to set the goal)
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :param bool_pac_man: tell if the maze is in pacman mode.
    :type bool_pac_man: bool
    :param maze_element: a maze
    :type maze_element: maze.Maze
    :return: None
    :side effect: user can click to resolve or set a goal in the maze.
    :UC: canvas must be an active Canvas
    """
    if bool_pac_man:
        canvas.bind('<Button-1>',
                 lambda event: lets_go_pac_man(canvas, event, maze_element))
    else:
        canvas.bind('<Button-1>',
             lambda event: lets_go(canvas, event, maze_element))
    canvas.bind('<Button-3>',
             lambda event: change_goal(canvas, event, maze_element))

def unbinding_buttons(canvas):
    """
    Disable user's clicks by unbinding the left and the right click of the mouse to actions. (left to resolve the maze, right to set the goal)
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :return: None
    :side effect: user can't click (in particular if a resolution is in progress).
    :UC: canvas must be an active Canvas
    """
    canvas.unbind('<Button-1>')
    canvas.unbind('<Button-3>')
    
    
#####################
# Drawing functions #
#####################

def recursive_spaw_pac_man(canvas, x, y, direction, frame=0):
    """
    Create pacman's spawn animation by consecutive additions of frames. 
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :param x: current room's position in the x-axis of pacman
    :type x: int
    :param y: current room's position in the y-axis of pacman
    :type y: int
    :param direction: the direction where the pacman will go
    :type direction: str
    :param frame: the part of the pacman that we do create (default is 0 ; maximum is 30) (it's a geometric arc)
    :type frame: int
    :return: None
    :side effect: modifies the pacman style during the spawning (before resolution in pacman mode)
    :UC: canvas must be an active Canvas
    """
    if frame > 30:
        pass
    else:
        ray = DIMENSION_ROOM // 3
        if ray == 0:
            ray = 1

        sight = direction - (frame*4)
        canvas.create_arc(x - ray, y - ray, x + ray, y + ray, start=sight, extent=frame*8,  
                              fill = 'yellow', tags = 'pac_man')
        canvas.update()
        time.sleep(1/60)
        erase_circle(canvas, 'pac_man')
        recursive_spaw_pac_man(canvas, x, y, direction, frame+1)
        
def draw_spawn_pac_man(canvas, x, y, x_go, y_go):
    """
    Draw pacman in the good direction when he spawn.
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :param x: current room's position in the x-axis of pacman
    :type x: int
    :param y: current room's position in the y-axis of pacman
    :type y: int
    :param x_go: next room's position in the x-axis
    :type x_go: int
    :param y_go: next room's position in the y-axis
    :type y_go: int
    :return: None
    :side effect: draw pacman's mouth in the good direction when he spawn.
    :UC: canvas must be an active Canvas
    """ 
    direction_sight = {'RIGHT': 180, 'UP': 270, 'LEFT': 0, 'DOWN': 90}
    
    direction = compare_coordinates_pac_man(x, y, x_go, y_go)
    x = (x * DIMENSION_ROOM) + (DIMENSION_ROOM//2)
    y = (y * DIMENSION_ROOM) + (DIMENSION_ROOM//2)
    
    recursive_spaw_pac_man(canvas, x, y, direction_sight[direction])

def draw_pac_man(canvas, x, y, direction, mouth):
    """
    Draw a pacman at the position (x,y)
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :param x: position in the x-axis of the pacman
    :type x: int
    :param y: position in the y-axis of the pacman
    :type y: int
    :param direction: the direction where the pacman will go
    :type direction: str
    :param mouth: the state of the pacman mouth's open or middle opening
    :type mouth: str
    :return: None:
    :side effect: draw a pacman at a position
    :UC: canvas must be an active Canvas
    """
    direction_sight = {'RIGHT': 60, 'UP': 60+90, 'LEFT': 60+90*2, 'DOWN': 60+90*3}
    mouth_state = {'OPEN': 360-120, 'MIDDLE': 360-60}
    
    ray = DIMENSION_ROOM // 3
    if ray == 0:
        ray = 1
    if mouth == 'CLOSE':
        canvas.create_oval(x - ray, y - ray, x + ray, y + ray,  
                              fill = 'yellow', tags = 'pac_man')
    else:
        sight = direction_sight[direction] - (mouth_state[mouth] - 240)//2
        canvas.create_arc(x - ray, y - ray, x + ray, y + ray, start=sight, extent=mouth_state[mouth],  
                              fill = 'yellow', tags = 'pac_man')
    canvas.update()
        
def draw_circle(canvas, x, y, color, indice):
    """
    Draw a circle at the position (x,y)
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :param x: position in the x-axis of the circle
    :type x: int
    :param y: position in the y-axis of the circle
    :type y: int
    :param color: the color of the circle
    :type color: str
    :param indice: index of the circle
    :type indice: int
    :return: None:
    :side effect: draw a circle at a position
    :UC: canvas must be an active Canvas
    """
    if color == 'white':
        ray = DIMENSION_ROOM // 8
    else: 
        ray = DIMENSION_ROOM // 4
    if ray == 0:
        ray = 1
    canvas.create_oval(x - ray, y - ray, x + ray, y + ray, 
                              fill = color, tags = color+'{:d}'.format(indice))
    canvas.update()
    
    
def draw_grid(canvas, maze_element, width, height):
    """
    Draw a grid of maze.
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :param maze_element: a maze
    :type maze_element: maze.Maze
    :param width: width of the maze
    :type width: int
    :param height: height of the maze
    :type height: int
    :return: None
    :side effect: draw a grid of maze used in the graphical interface
    :UC: canvas must be an active Canvas
    """
    canvas.create_line(0,0, width * DIMENSION_ROOM - 1, 0,
                       fill=GRID_COLOR, width=1)
    canvas.create_line(0, 0, 0, height * DIMENSION_ROOM - 1,
                       fill=GRID_COLOR, width=1)

    for y in range(height):
        for x in range(width):
            if not maze_element.get_room(x,y).bottom_door_is_open():
                canvas.create_line(x * DIMENSION_ROOM, (y+1) * DIMENSION_ROOM,
                                   (x+1) * DIMENSION_ROOM, (y+1) * DIMENSION_ROOM,
                                   fill=GRID_COLOR, width=1)
            if not maze_element.get_room(x,y).right_door_is_open():
                canvas.create_line((x+1) * DIMENSION_ROOM, y * DIMENSION_ROOM,
                                   (x+1) * DIMENSION_ROOM, (y + 1) * DIMENSION_ROOM,
                                   fill=GRID_COLOR, width=1)
  
  
def erase_circle(canvas, color):
    """
    Delete circles which have the color color.
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :param color: the color of the circle
    :type color: str
    :return: None:
    :side effect: delete circles
    :UC: canvas must be an active Canvas
    """
    delete = canvas.gettags(color)
    canvas.delete(delete)

##############
# Resolution #
##############

def change_goal(canvas, event, maze_element):
    """
    Change the goal of a maze.
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :param event: an user event (specifically here a right click event)
    :type event: tkinter.event
    :param maze_element: a maze
    :type maze_element: maze.Maze
    :return: None
    :side effect: modify the goal position of a maze.
    :UC: canvas must be an active Canvas
    """
    i = 0
    circles = PATH_COLOR+'{:d}'.format(i)
    while len(canvas.gettags(circles)) != 0:
        erase_circle(canvas, circles)
        canvas.update()
        i += 1
        circles = PATH_COLOR+'{:d}'.format(i)
    erase_circle(canvas, GOAL_COLOR+'0')
    erase_circle(canvas, 'pac_man')

    x_goal = event.x // DIMENSION_ROOM
    y_goal = event.y // DIMENSION_ROOM
    maze_element.set_goal(x_goal,y_goal)

    x, y = coordinate_center(event.x, event.y)
    draw_circle(canvas, x, y, GOAL_COLOR, 0)


def lets_go(canvas, event, maze_element):
    """
    Display the way between a left-clicked room and the goal room.
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :param event: an user event (specifically here a left click event)
    :type event: tkinter.event
    :param maze_element: a maze
    :type maze_element: maze.Maze
    :return: None
    :side effect: resolves the maze and display the solution if its possible
    :UC: canvas must be an active Canvas
    """
    unbinding_buttons(canvas)
    
    i = 0
    circles = PATH_COLOR+'{:d}'.format(i)
    while len(canvas.gettags(circles)) != 0:
        erase_circle(canvas, circles)
        i += 1
        canvas.update()
        circles = PATH_COLOR+'{:d}'.format(i)

    x, y = event.x // DIMENSION_ROOM, event.y // DIMENSION_ROOM
    x_goal, y_goal = maze_element.get_goal()
    maze_element.resolve(x,y, x_goal, y_goal)

    list_resolve = maze_element.get_solution()

    i = 0
    n = len(list_resolve)
    while i < n-1:
        x,y = list_resolve[i]
        x = (x * DIMENSION_ROOM) + (DIMENSION_ROOM//2)
        y = (y * DIMENSION_ROOM) + (DIMENSION_ROOM//2)
        draw_circle(canvas, x, y, PATH_COLOR, i)
        i += 1
        time.sleep(SPEED_PATH)
        
    binding_buttons(canvas, False, maze_element)
    
    
def lets_go_pac_man(canvas, event, maze_element):
    """
    Display the way between a left-clicked room and the goal room in the pacman representation.
    
    :param canvas: the tkinter canvas of the active maze
    :type canvas: Canvas
    :param event: an user event (specifically here a left click event)
    :type event: tkinter.event
    :param maze_element: a maze
    :type maze_element: maze.Maze
    :return: None
    :side effect: resolves the maze and display the solution if its possible in the pacman representation
    :UC: canvas must be an active Canvas
    """
    unbinding_buttons(canvas)
    
    erase_circle(canvas, 'pac_man')
    lets_go(canvas, event, maze_element)

    unbinding_buttons(canvas)
    
    solution = maze_element.get_solution()
    time.sleep(0.25)
    x, y = solution[0]
    x_go, y_go = solution[1]
    draw_spawn_pac_man(canvas, x, y, x_go, y_go)
    
    i=0
    n = len(solution)
    while i < n-1:
        x,y = solution[i]
        x_go, y_go = solution[i+1]
        erase_circle(canvas, 'white{:d}'.format(i))
        animation_one_room_pac_man(canvas, x, y, x_go, y_go)
        i+=1
    
    erase_circle(canvas, 'pac_man')
    x = (x_go * DIMENSION_ROOM) + (DIMENSION_ROOM//2)
    y = (y_go * DIMENSION_ROOM) + (DIMENSION_ROOM//2)
    draw_pac_man(canvas, x, y, 'UP', 'CLOSE')
    binding_buttons(canvas, True, maze_element)

#####################
# Display functions #
#####################

def display_graphical(arg, maze_element):
    """
    Display a graphical representation of a maze using tkinter module.
    
    :param arg: an array of arguments
    :type arg: argparse.Namespace
    :param maze_element: a maze
    :type maze_element: maze.Maze
    :return: None
    :side effect:
        * Create a graphical grid which represent a maze by walls.
        * The goal of the maze by a point.
        * Create interactivity : by a right click on a room we can change the goal of the maze, by a left click on a room we try to find a way from the room to the goal.
    :UC: None
    """
    can_width = maze_element.get_width()*DIMENSION_ROOM
    can_height = maze_element.get_height()*DIMENSION_ROOM

    win = Tk()
    win.title('Maze {:d}x{:d}'.format(maze_element.get_width(), maze_element.get_height()))
    can = Canvas(win, bg=BG_COLOR, width=can_width, height=can_height)
    
    x, y = coordinate_center(can_width-1, can_height-1)
    draw_circle(can, x, y, GOAL_COLOR, 0)
    binding_buttons(can, arg.pac_man, maze_element)

    can.pack()
    draw_grid(can, maze_element, maze_element.get_width(), maze_element.get_height())
    win.mainloop()

##################
# Main functions #
##################

def usage():
    """
    Display an usage message.
    
    :return: None
    :side effect: print an usage message
    :UC: None
    """
    print('Usage : Please consult the help with the argument -h or --help of argparse \n\tOr consult the manual with the argument -m or --manual')
    exit(1)


def main(arg):
    """
    Main function which transform user inputs into concrete event.
    
    :param arg: an array of arguments
    :type arg: argparse.Namespace
    :return: None
    :side effect: execute user inputs
    :UC: None
    """
    if arg.documentation:
        with open('../manual/graphical_manual.txt', 'r') as manual:
            print(manual.read())
        sys.exit()
    elif arg.library:
        library.print_library('../maze_library/create')
        sys.exit()
    elif  arg.random != None or arg.file != None:
        if arg.file != None:
            if arg.random != None:
                print("You cannot use -f and -r in the same time")
                sys.exit()
            else:
                try:
                    m = maze.Maze.create_from_file('../maze_library/create/'+arg.file[0])
                except:
                    print('Your files is not good.')
                    sys.exit()         
        elif arg.random != None:
            if len(arg.random) == 0:
                m = maze.Maze()
                m.random()
            elif len(arg.random) == 2:
                x, y = arg.random
                if x > 100 or y > 100:
                    sys.setrecursionlimit(x*y)
                m = maze.Maze(x,y)
                m.random()
            else:
                print('Your dimensions is not good')
                sys.exit()

        if arg.save != None:
            file = arg.save[0]
            if file[-4:] != '.txt':
                file += '.txt'
            m.write_in_file('../maze_library/saves/'+file)

        display_graphical(arg, m)
        sys.exit()

    usage()
    
    
if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Help for graphical maze's script")

    parser.add_argument('-m', '--manual', action='store_true', dest='documentation', help='display the manual of the script')
    parser.add_argument('-l', '--library', action='store_true', dest='library', help='display all maze available')
    parser.add_argument('-f', '--file', action='store', dest='file', type=str, nargs=1, help='create a maze from a file')
    parser.add_argument('-r', '--random', action='store', dest='random', type=int, nargs='*', help='create a random maze (take 0 or 2 arguments)')
    parser.add_argument('-s', '--save', action='store', dest='save', type=str, nargs=1, help='save your maze')
    parser.add_argument('-cw', '--color_wall', action='store', dest='color_wall', type=str, nargs=1, help='change the color of the walls (default=BLACK)')
    parser.add_argument('-cr', '--color_room', action='store', dest='color_room', type=str, nargs=1, help='change the color of the rooms (default=WHITE)')
    parser.add_argument('-cg', '--color_goal', action='store', dest='color_goal', type=str, nargs=1, help='change the color of the goal (default=BLUE)')
    parser.add_argument('-cp', '--color_path', action='store', dest='color_path', type=str, nargs=1, help='change the color of the path (default=RED)')
    parser.add_argument('-sr', '--size_room', action='store', dest='size_room', type=int, nargs=1, help='change the size of each rooms (default=20)')
    parser.add_argument('-sp', '--speed_path', action='store', dest='speed_path', type=int, nargs=1, help='change the speed of the path in milliseconds (defaut=50)')
    parser.add_argument('-pm', '--pac-man', action='store_true', dest='pac_man', help='launch the pac-man mode')

    args = parser.parse_args()

    if args.size_room == None:
        DIMENSION_ROOM = 20
    else:
        DIMENSION_ROOM = args.size_room[0]
    
    if args.pac_man:
        BG_COLOR = 'black'
    elif args.color_room == None:
        BG_COLOR = 'white'
    else:
        BG_COLOR = args.color_room[0]
    
    if args.pac_man:
        GRID_COLOR = 'purple'
    elif args.color_wall == None:
        GRID_COLOR = 'black'
    else:
        GRID_COLOR = args.color_wall[0]

    if args.pac_man:
        GOAL_COLOR = 'grey'  
    elif args.color_goal == None:
        GOAL_COLOR = 'blue'
    else:
        GOAL_COLOR = args.color_goal[0]

    if args.pac_man:
        PATH_COLOR = 'white'
    elif args.color_path == None:
        PATH_COLOR = 'red'
    else:
        PATH_COLOR = args.color_path[0]

    if args.pac_man:
        SPEED_PATH = 0
    elif args.speed_path == None:
        SPEED_PATH = 5/100
    else:
        SPEED_PATH = args.speed_path[0] / 1000

    main(args)
