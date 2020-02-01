#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""
:mod:`Maze` module

:author: BONVOISIN Alexandre, PHU Valentin, VALET Tristan

:date: 2018, November

Provides 

- a class :class:`MazeState` for the state of a maze
- an exception :class:`MazeError`
- a function `adjacent_coordinates`
- a class :class:`Maze` for create maze

This module provides functions and a class for Maze's game's management.

"""

from enum import Enum
from room import *
from stack import Stack
import random

class MazeState(Enum):
    """
    A class to define an enumerated type with four values :

    * ``not_ready``
    * ``ready``
    * ``solved``
    * ``impossible``

    for the four states of maze game.
    """
    not_ready = 1
    ready = 2
    solved = 3
    impossible = 4

class MazeError(Exception):
    """
    Exception for the class Maze
    """
    def __init___(self, msg):
        """
        Generates the error.
        """
        self.message = msg

def adjacent_coordinates(width, height ,x , y):
    """
    Return a list of the adjacent coordinates from x,y in a grid.
        * The first element is the top neighbor.
        * The second element is the right neighbor.
        * The third element is the bottom neighbor.
        * The forth element is the left neighbor.
        * None for no neighbor.

    :param width: the width of your grid
    :type width: int
    :param height: the height of your grid
    :type height: int
    :param x: your position on x-axis
    :type x: int
    :param y: your position on y-axis
    :type y: int
    :return: a list of the adjacent coordinnates
    :rtype: list
    :UC: width > 0 and height > 0 and 0 <= x < width and 0 <= y < height
    :Examples:

    >>> adjacent_coordinates(10,10,5,5)
    [(5, 4), (6, 5), (5, 6), (4, 5)]
    >>> adjacent_coordinates(5,5,4,4)
    [(4, 3), None, None, (3, 4)]
    >>> adjacent_coordinates(5,5,0,0)
    [None, (1, 0), (0, 1), None]
    >>> adjacent_coordinates(5,5,4,0)
    [None, None, (4, 1), (3, 0)]
    >>> adjacent_coordinates(5,5,7,0)
    Traceback (most recent call last):
      ...
    IndexError: You are not in the grid
    """
    if not 0 <= x < width or not 0 <= y < height:
        raise IndexError('You are not in the grid')
    
    res = [None for k in range(4)]
    if y != 0 :
        res[0] = (x,y-1)
    if x+1 < width:
        res[1] = (x+1,y)
    if y+1 < height:
        res[2] = (x,y+1)
    if x != 0 :
        res[3] = (x-1,y)
    return res

class Maze():
    """
    Create a maze.

    A maze is composed by 2 arguments, width and height of itself. (By default, its' a Maze 5x5)

    The maze have a goal (By default, it's the coordinate (width-1, height-1)) and a state (By default, it's not_ready)

    At the creation, the maze have rooms with all doors closed.

    The object Maze have some many methods:
     - Builders :
         * Maze.create_from_file()
         
     - Modifiers :
         * Maze.set_goal()
         * Maze.set_state()

     - Selectors :
         * Maze.get_width()
         * Maze.get_height()
         * Maze.get_state()
         * Maze.get_goal()
         * Maze.get_room()
         * Maze.get_solution()

     - for the editing :
         * Maze.change_room()
         * Maze.change_row()
         * Maze.end_edit()

     - for the random :
         * Maze.random()

     - for the resolution :
         * Maze.resolve()

     - for the save :
         * Maze.write_in_file()
    """
    ###########
    # BUILDER #
    ###########
    
    def __init__(self, width = 5, height = 5):
        """
        Build a maze grid of size width*height.

        :param width: [optional] horizontal size of game (default = 5)
        :type width: int
        :param height: [optional] vertical size of game (default = 5)
        :type height: int
        :return: a new grid of size width*height.
        :rtype: Maze
        :UC: width and height must be positive integers.
        :Examples:
        
        >>> maze = Maze()
        >>> maze.get_width()
        5
        >>> maze.get_height()
        5
        >>> maze.get_state() == MazeState.not_ready
        True
        """
        if width <=0 or height<=0:
            raise MazeError("Your width and your height must be positive integer")
        self.__width = width
        self.__height = height
        self.__state = MazeState.not_ready
        self.__goal = ( width-1, height -1)
        self.__maze_list = [[ Room((width*j)+i) for i in range(width)] for j in range(height)]
        self.__resolve_list = []


    #############
    # SELECTORS #
    #############

    def get_height(self):
        """
        Return height of the maze.
    
        :return: height of the self grid
        :rtype: int
        :UC: None
        :Examples:

        >>> m = Maze()
        >>> m.get_height()
        5
        """
        return self.__height

    def get_width(self):
        """
        Return width of the maze.

        :return: width of the self grid
        :rtype: int
        :UC: None
        :Examples:

        >>> m = Maze()
        >>> m.get_width()
        5
        """
        return self.__width

    def get_state(self):
        """
        Return the state of the maze.
        
        :return: the state of the maze game
        :rtype: MazeState
        :UC: None
        :Examples:
        
        >>> m = Maze()
        >>> m.get_state() == MazeState.not_ready
        True
        """
        return self.__state

    def get_room(self,x,y):
        """
        Return the room in the position (x,y) in the maze.
        
        :param x: position in the x-axis
        :type x: int
        :param y: position in the y-axis
        :type y: int
        :return: the room on the position (x,y)
        :rtype: room.Room
        :UC: 0 <= x < maze.get_width() and 0 <= y < maze.get_height()
        :Examples:

        >>> m = Maze()
        >>> m.get_room(2,3)
         |
        -+
        """
        if not 0 <= x < self.get_width():
            raise MazeError("Your x coordinate doesn't exist")
        elif not 0 <= y < self.get_height():
            raise MazeError("Your y coordinate doesn't exist")
        
        return self.__maze_list[y][x]

    def get_solution(self):
        """
        Return the list of the rooms on the way of solution.

        :return: the solution way
        :rtype: list
        :UC: None
        :Examples:

        >>> m = Maze.create_from_file('../maze_library/create/maze_5_5_0.txt')
        >>> m.get_solution()
        []
        >>> m.resolve()
        >>> m.get_solution()
        [(0, 0), (0, 1), (0, 2), (1, 2), (1, 3), (2, 3), (2, 4), (3, 4), (4, 4)]
        """
        return self.__resolve_list

    def get_goal(self):
        """
        Return the goal of the maze. By default, that's the room in position (width-1, height-1).

        :return: the room which is the goal of the solution
        :rtype: tuple
        :UC: none
        :Examples:

        >>> m = Maze()
        >>> m.get_goal()
        (4, 4)
        >>> m.set_goal(0,3)
        >>> m.get_goal()
        (0, 3)
        """
        return self.__goal


    #############
    # MODIFIERS #
    #############
  
    def set_state(self, state):
        """
        Change the state of a maze.

        :param state: the state
        :type state: MazeState
        :side effect: the state of maze changes
        :UC: None
        :Examples:

        >>> m = Maze()
        >>> m.set_state(MazeState.ready)
        >>> m.get_state()
        <MazeState.ready: 2>
        >>> m.set_state(1)
        Traceback (most recent call last):
          ...
        MazeError: The state is not good.
        """
        if not isinstance(state, MazeState):
            raise MazeError('The state is not good.')
        self.__state = state

    def set_goal(self, x, y):
        """
        Change the goal room of a maze.

        :param x: the x-axis coordinates of the goal room
        :type x: int
        :param y: the y-axis coordinate of the goal room
        :type y: int
        :side effect: the goal room of maze changes
        :UC: None
        :Examples:

        >>> m = Maze()
        >>> m.set_goal(3,2)
        >>> m.get_goal()
        (3, 2)
        >>> m.set_goal(5,3)
        Traceback (most recent call last):
          ...
        MazeError: Your coordinate is not good.
        """
        if not 0 <= x < self.get_width() or not 0 <= y < self.get_height():
            raise MazeError('Your coordinate is not good.')
        self.__goal = (x, y)  


    ############
    # SPECIALS # 
    ############
    
    def __repr__(self):
        """
        Represent the maze.

        :return: the representation of the self maze
        :rtype: str
        :UC: None
        :Examples:

        >>> m = Maze.create_from_file('../maze_library/create/maze_5_5_0.txt')
        >>> m
        +-+-+-+-+-+
        |       | |
        + + +-+-+ +
        | |   |   |
        + +-+ + +-+
        |   | |   |
        +-+ +-+ +-+
        |     | | |
        +-+ + + + +
        |   |     |
        +-+-+-+-+-+
        """
        rep = '+' + ('-+' * self.__width) + '\n'
        for i in self.__maze_list:
            rep += Room.printable(i) + '\n'
        rep=rep[0:-1]
        return rep


    ############
    # Handmade #
    ############

    def __check_str(string):
        """
        Check if a string is good for the mutation of room.

        :param string: your string
        :type string: str
        :return:
            * True, if the string is good
            * False otherwise
        :rtype: bool
        :UC: The string must:
            * contains only '|', ' ', '-'
            * even index must be ' ' or '-'
            * odd index must be ' ' or '|'
        """
        liste_char = ['|', ' ', '-']
        i = 0        
        while i < len(string):
            if i%2 == 0 and not string[i] in liste_char[1:]:
                return False
            elif i%2 == 1 and not string[i] in liste_char[:2]:
                return False
            i += 1
        return True
        
    def change_room(self,x,y,string):
        """
        Change room's state by taking the shape of the pattern given in string.
        
        :param x: the x-axis coordinates of the room you would modify
        :type x: int
        :param y: the y-axis coordinates of the room you would modify
        :type y: int
        :param string: The pattern of the room
        :type string: str
        :return: None
        :side effect: the room in position (x,y) in the maze is modified
        :UC:
            * len(string) == 2
            * string must contains only ' ' or '-' in even index and ' ' or '|' in odd index
            * maze must be in editable state : self.get_state() == MazeState.not_ready
            * even index can't be ' ' if y == height of the maze -1
            * odd index can't be ' ' if x == width of the maze -1
        :Examples:
        
        >>> m=Maze(3,3)
        >>> m
        +-+-+-+
        | | | |
        +-+-+-+
        | | | |
        +-+-+-+
        | | | |
        +-+-+-+
        >>> m.change_room(1,1,'- ')
        >>> m
        +-+-+-+
        | | | |
        +-+-+-+
        | |   |
        +-+-+-+
        | | | |
        +-+-+-+
        """
        if not Maze.__check_str(string) or len(string) != 2:
            raise MazeError('Your string is not correct')
        elif ( x == (self.get_width()-1) and string[1] == ' ' ) or \
             ( y == (self.get_height()-1) and string[0] == ' ' ):
            raise MazeError('You cannot open the door on the edge of maze')
        elif self.get_state() != MazeState.not_ready:
            raise MazeError('The maze is not editable')

        self.get_room(x,y).close_all_door()
        if string[0] == ' ':
            self.get_room(x,y).open_bottom_door()
            
        if string[1] == ' ':
            self.get_room(x,y).open_right_door()

    def change_row(self, row, list_string):
        """
        Change rooms states in a row of the maze, by taking shapes in given patterns in the list of string.
        
        :param row: the line you would modify
        :type row: int
        :param list_string: patterns of rooms in the row you would
        :type list_string: list
        :side effect: rooms in the row are modified in the maze
        :UC:
            * len(string) == width of the maze
            * 0 <= row < height of the maze
            * string in list must contains only ' ' or '-' in even index and ' ' or '|' in odd index
            * maze must be in editable state : self.get_state() == MazeState.not_ready
            * even index can't be ' ' if y == height of the maze -1
            * odd index can't be ' ' if x == width of the maze -1
        :Examples:
        
        >>> m=Maze()
        >>> m
        +-+-+-+-+-+
        | | | | | |
        +-+-+-+-+-+
        | | | | | |
        +-+-+-+-+-+
        | | | | | |
        +-+-+-+-+-+
        | | | | | |
        +-+-+-+-+-+
        | | | | | |
        +-+-+-+-+-+
        >>> m.change_row(2,['  ','-|','- ','-|',' |'])
        >>> m
        +-+-+-+-+-+
        | | | | | |
        +-+-+-+-+-+
        | | | | | |
        +-+-+-+-+-+
        |   |   | |
        + +-+-+-+ +
        | | | | | |
        +-+-+-+-+-+
        | | | | | |
        +-+-+-+-+-+
        """
        if self.get_width() != len(list_string):
            raise MazeError('Your list of string is too large or not enough')
        elif not 0 <= row < self.get_height():
            raise MazeError("Your row doesn't exist")
        elif self.get_state() != MazeState.not_ready:
            raise MazeError('The maze is not editable')
        i = 0
        while i < len(list_string):
            self.change_room(i,row,list_string[i])
            i+=1

    def end_edit(self):
        """
        Disable the editing mode.
        
        :return: None
        :side effet: maze state become ready
        :UC: None
        :Examples:
        
        >>> m=Maze()
        >>> m.get_state()
        <MazeState.not_ready: 1>
        >>> m.end_edit()
        >>> m.get_state()
        <MazeState.ready: 2>
        """
        self.set_state(MazeState.ready)

    ####################
    # Create from file #
    ####################

    def __two_str_to_one_str(str1,str2):
        """
        Create a list which contain strings of length 2.
        
        :param str1: first string
        :type str1: str
        :param str2: second string
        :type str2: str
        :return: a list which contain strings of length 2
        :side effect: delete strings which contain the string : '\n'
        :rtype: list
        :UC: None
        """
        if str1 == "\n" or str2 == "\n":
            return []
        else:
            return [str2[0] + str1[1]] + Maze.__two_str_to_one_str(str1[2:],str2[2:])

    def __list_processing(list_str):
        """
        Create a list which contain strings of indetermined length into a list which contain strings of length 2.
        
        :param list_str: list which contain a great string
        :type list_str: list
        :return: a list which contain many strings of length 2.
        :rtype: list
        :UC: None
        """
        if len(list_str) < 2:
            return []
        else:
            str1 = list_str[0][1:]
            str2 = list_str[1][1:]
            res = Maze.__two_str_to_one_str(str1,str2)
            return [res] + Maze.__list_processing(list_str[2:])
    

    def __read_maze_file(file):
        """
        Read a file that contain a maze pattern.
        
        :param file: name of the file containing the maze pattern
        :type file: str
        :return: the width of the maze, the heigh of the maze , a list provided by the maze pattern
        :rtype: tuple
        :UC: the file was writing like :
            * First line is an integer that represent the width
            * Second line is an integer that represent the height
            * The following lines are read in the range [3, height] and is the pattern of the maze.
        """
        liste_read = []
        r = file.readline()
        while r != '':
            liste_read.append(r)
            r = file.readline()
        return (int(liste_read[0]),int(liste_read[1]),Maze.__list_processing(liste_read[3:]))

    def create_from_file(file):
        """
        Create a maze from a file.
        
        :param file: name of the file containing the maze pattern
        :type file: str
        :return: a maze from the file
        :rtype: Maze
        :UC: file must exist
        :Examples:
        
        >>> m = Maze.create_from_file('../maze_library/create/maze_5_5_0.txt')
        >>> m
        +-+-+-+-+-+
        |       | |
        + + +-+-+ +
        | |   |   |
        + +-+ + +-+
        |   | |   |
        +-+ +-+ +-+
        |     | | |
        +-+ + + + +
        |   |     |
        +-+-+-+-+-+
        """
        try:
            with open(file, 'r')as read_file:
                width, height, list_str = Maze.__read_maze_file(read_file)
        except FileNotFoundError:
            raise MazeError('Please give an existing file.') from None
        m = Maze(width, height)
        i = 0
        while i < height:
            m.change_row(i, list_str[i])
            i+=1
        return m
    
    #######################
    # Write Maze in file  #
    #######################

    def write_in_file(self,file):
        """
        Write the maze in a file.
        
        :param file: Name of the file you would write (save) the maze
        :type file: str
        :return: None
        :UC: None
        :Examples:
        
        >>> m=Maze()
        >>> m.random()
        >>> m.write_in_file('../maze_library/saves/writed_in_file_random_example.txt')
        """
        with open(file, 'w') as write_file:
            string = str(self.get_width()) + '\n' + str(self.get_height()) + '\n' + str(self)
            write_file.write(string)

    ################
    # Possible way #
    ################

    def __way_possible(self,x,y):
        """
        Find the possible way to go from one room to another.
        
        :param x: the x-axis coordinates of the room
        :type x: int
        :param y: the y-axis coordinates of the room
        :type y: int
        :return: all possible way in the maze
        :rtype: list
        :UC: None
        """
        liste_room = adjacent_coordinates(self.get_width(),self.get_height(),x,y)
        result = []
        
        if liste_room[0] != None:
            if self.get_room(x,y).neighbor_top_is_open(self.get_room(x,y-1)):
                result.append(liste_room[0])
        if liste_room[1] != None:
            if self.get_room(x,y).right_door_is_open():
                result.append(liste_room[1])
        if liste_room[2] != None:
            if self.get_room(x,y).bottom_door_is_open():
                result.append(liste_room[2])
        if liste_room[3] != None:
            if self.get_room(x,y).neighbor_left_is_open(self.get_room(x-1,y)):
                result.append(liste_room[3])
        return result

    #######################################
    # Random generation of a perfect maze #
    #######################################
    
    def __compare_tag(room1,room2):
        """
        Compare tags between 2 rooms.
        
        :param room1: the first room
        :type room1: room.Room
        :param room2: the second room
        :type room2: room.Room
        :return: if tags are differents (which is > in this case) or equals :
            * -1 if tag of room1 is < tag of room2
            * 0 if tags of 2 rooms are the same
            * 1 if tag of room1 is > tag of room2
        :rtype: int
        :UC: type(room1) == type(room2)
        """
        if type(room1)!= type(room2):
            raise MazeError('Elements are not comparable')
        tag_room1 = room1.get_tag()
        tag_room2 = room2.get_tag()
        if tag_room1 < tag_room2:
            return -1
        elif tag_room1 == tag_room2:
            return 0
        else:
            return 1
            
    def __change_tag_snowball(self,x1,y1,x2,y2):
        """
        Change tags like a snowball effect.
        
        :param x1: the x-axis coordinates of the first room where we would modify the tag
        :type x1: int
        :param y1: the y-axis coordinates of the first room where we would modify the tag
        :type y1: int
        :param x2: the x-axis coordinates of the second room where we would modify the tag
        :type x2: int
        :param y2: the y-axis coordinates of the second room where we would modify the tag
        :type y2: int
        :return: None
        :side effect: Change tags of room like a snowball effect (that is if we modify the tag of one room, an other room can be impacted).
        :UC: None
        """
        tag_room1 = self.get_room(x1,y1).get_tag()
        tag_room2 = self.get_room(x2,y2).get_tag()
        comp = Maze.__compare_tag(self.get_room(x1,y1),self.get_room(x2,y2))

        if comp == 0:
            pass
        elif comp < 1:
            self.get_room(x2,y2).set_tag(tag_room1)
            way = self.__way_possible(x2,y2)
            for i in way:
                x1, y1 = i
                self.__change_tag_snowball(x1,y1,x2,y2)
        else:
            self.get_room(x1,y1).set_tag(tag_room2)
            way = self.__way_possible(x1,y1)
            for i in way:
                x2, y2 = i
                self.__change_tag_snowball(x1,y1,x2,y2)

    def random(self):
        """
        Modify the current maze to a perfect maze by a random generation.
        
        :return: None
        :side effect: the current maze was modified into a perfect maze
        :UC: None
        """
        i = 0
        while i < self.get_width()*self.get_height()-1:
            x = random.randrange(self.get_width())
            y = random.randrange(self.get_height())
            rand_right, rand_bottom = random.randrange(2), random.randrange(2)
            try:
                comp = Maze.__compare_tag(self.get_room(x,y),self.get_room(x+1,y))
                if comp != 0 and rand_right == 1:
                    self.get_room(x,y).open_right_door()
                    self.__change_tag_snowball(x,y,x+1,y)
                    i+=1
            except MazeError:
                pass
            try:
                comp = Maze.__compare_tag(self.get_room(x,y),self.get_room(x,y+1))
                if comp != 0 and rand_bottom == 1:
                    self.get_room(x,y).open_bottom_door()
                    self.__change_tag_snowball(x,y,x,y+1)
                    i+=1
            except MazeError:
                pass

    ##############
    # Resolution #
    ##############

    def __clean_tag(self):
        """
        Reset tag of all rooms in the maze.
        
        :return: None
        :side effect: reset rooms's tag
        :UC: None
        """
        k = 0
        for i in self.__maze_list:
            for j in i:
                j.set_tag(k)
                k += 1

    def __clean_stack(stack):
        """
        Empties a stack composed by other stacks.
        
        :param stack: a stack composed by other stacks.
        :type stack: stack.Stack
        :return: An empty list
        :rtype: list
        :UC: None
        """
        if type(stack)!= type(Stack()):
            raise MazeError('stack type expected')
        if stack.is_empty():
            return []
        elif stack.top().is_empty():
            stack.pop()
            return Maze.__clean_stack(stack)
        else:
            element = stack.top().pop()
            return Maze.__clean_stack(stack) + [element]

    def __stackable(self, stack, x_from, y_from, x, y):
        """
        Access to all rooms of the maze, if we can, the state is changed to solved then the maze is revolvable.
        
        :param stack: a stack which contain stacks (the main stack is the way traveled, internal stack are the way intersection)
        :type stack: stack.Stack
        :param x_from: the x-axis coordinates of the previous room
        :type x_from: int
        :param y_from: the y-axis coordinates of the previous room
        :type y_from: int
        :param x: the x-axis coordinates of the current room
        :type x: int
        :param y: the y-axis coordinates of the current room
        :type y: int
        :return: None
        :side effect: change the maze's state : MazeState.solved
        :UC: None
        """
        if stack.top().top() != self.get_goal():
            way = self.__way_possible(x,y)
            try:
                way.remove((x_from, y_from))
            except ValueError:
                pass
            length_way = len(way)

            if length_way == 0:
                stack.pop()
            elif length_way == 1:
                x_next, y_next = way[0]
                stack.top().push(way[0])
                self.__stackable(stack,x,y,x_next,y_next)
            else:
                for i in way:
                    if stack.top().top() != self.get_goal():
                        x_next, y_next = i
                        new_stack = Stack()
                        new_stack.push(i)
                        stack.push(new_stack)
                        self.__stackable(stack,x,y,x_next,y_next)
                        
                if stack.top().top() != self.get_goal():
                    stack.pop()
        else:
            self.set_state(MazeState.solved)
                             
                             
    def resolve(self,x=0,y=0, x_goal = None, y_goal = None):
        """
        Resolve the maze, that is searching the way to go from one point to another of the maze.
        
        :param x: [optional] the x-axis coordinates of the room you would start the resolution
        :type x: int
        :param y: [optional] the y-axis coordinates of the room you would start the resolution
        :type y: int
        :param x_goal: [optional] the x-axis coordinates of the room you would finish the resolution (default : the room in the x coordinates width of the maze -1)
        :type x_goal: int
        :param y_goal: [optional] the y-axis coordinates of the room you would finish the resolution (default : the room in the y coordinates height of the maze -1)
        :type y_goal: int
        :return: None
        :side effect: Find the way to go from one point to another of the maze
        :UC: None
        :Examples:
        
        >>> m = Maze.create_from_file('../maze_library/create/maze_5_5_0.txt')
        >>> m
        +-+-+-+-+-+
        |       | |
        + + +-+-+ +
        | |   |   |
        + +-+ + +-+
        |   | |   |
        +-+ +-+ +-+
        |     | | |
        +-+ + + + +
        |   |     |
        +-+-+-+-+-+
        >>> m.resolve()
        >>> m.show_solution()
        >>> m
        +-+-+-+-+-+
        |o      | |
        + + +-+-+ +
        |o|   |   |
        + +-+ + +-+
        |o o| |   |
        +-+ +-+ +-+
        |  o o| | |
        +-+ + + + +
        |   |o o o|
        +-+-+-+-+-+
        """
        if x_goal != None and y_goal != None:
            self.set_goal(x_goal, y_goal)
        else:
            self.set_goal(self.get_width()-1, self.get_height()-1)
            
        try:
            self.hide_solution()
        except MazeError:
            pass

        self.__clean_tag()
        self.set_state(MazeState.ready)

        main_stack = Stack()
        second_stack = Stack()
        second_stack.push((x,y))
        main_stack.push(second_stack)

        self.__stackable(main_stack,x,y,x,y)

        if self.get_state() == MazeState.ready:
            self.set_state(MazeState.impossible)
            
        solution = Maze.__clean_stack(main_stack)
        self.__resolve_list = solution

    def show_solution(self):
        """
        Show the resolution.
        
        :return: None
        :side effect: show the resolution
        :UC:
            * maze's state != MazeState.impossible, your maze must be resolvable
            * maze's state == MazeState.solved, your maze must be solved
        :Examples:
        
        >>> m = Maze.create_from_file('../maze_library/create/maze_5_5_0.txt')
        >>> m
        +-+-+-+-+-+
        |       | |
        + + +-+-+ +
        | |   |   |
        + +-+ + +-+
        |   | |   |
        +-+ +-+ +-+
        |     | | |
        +-+ + + + +
        |   |     |
        +-+-+-+-+-+
        >>> m.resolve()
        >>> m.show_solution()
        >>> m
        +-+-+-+-+-+
        |o      | |
        + + +-+-+ +
        |o|   |   |
        + +-+ + +-+
        |o o| |   |
        +-+ +-+ +-+
        |  o o| | |
        +-+ + + + +
        |   |o o o|
        +-+-+-+-+-+
        """
        if self.get_state() == MazeState.impossible:
            raise MazeError('Your maze is impossible.')
        elif self.get_state() != MazeState.solved:
            raise MazeError('Your maze are not resolve for the moment. Please use the method resolve() for the resolution.')
        else:
            for i in self.__resolve_list:
                x, y = i
                self.get_room(x,y).set_tag('o')

    def hide_solution(self):
        """
        Hide the resolution.
        
        :return: None
        :side effect: hide the resolution
        :UC:
            * maze's state != MazeState.impossible, your maze must be resolvable
            * maze's state == MazeState.solved, your maze must be solved
        :Examples:
        
        >>> m = Maze.create_from_file('../maze_library/create/maze_5_5_0.txt')
        >>> m
        +-+-+-+-+-+
        |       | |
        + + +-+-+ +
        | |   |   |
        + +-+ + +-+
        |   | |   |
        +-+ +-+ +-+
        |     | | |
        +-+ + + + +
        |   |     |
        +-+-+-+-+-+
        >>> m.resolve()
        >>> m.show_solution()
        >>> m
        +-+-+-+-+-+
        |o      | |
        + + +-+-+ +
        |o|   |   |
        + +-+ + +-+
        |o o| |   |
        +-+ +-+ +-+
        |  o o| | |
        +-+ + + + +
        |   |o o o|
        +-+-+-+-+-+
        >>> m.hide_solution()
        >>> m
        +-+-+-+-+-+
        |       | |
        + + +-+-+ +
        | |   |   |
        + +-+ + +-+
        |   | |   |
        +-+ +-+ +-+
        |     | | |
        +-+ + + + +
        |   |     |
        +-+-+-+-+-+
        """
        if self.get_state() == MazeState.impossible:
            raise MazeError('Your maze is impossible.')
        elif self.get_state() != MazeState.solved:
            raise MazeError('Your maze are not resolve for the moment. Please use the method resolve() for the resolution.')
        else:
            for i in self.__resolve_list:
                x, y = i
                self.get_room(x,y).set_tag(0)


if __name__ == '__main__':
    import doctest
    doctest.testmod(optionflags=doctest.NORMALIZE_WHITESPACE | doctest.ELLIPSIS, verbose=False)
