#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""
:mod:`Room` module

:author: BONVOISIN Alexandre, PHU Valentin, VALET Tristan

:date: 2018, November

Provides 

- an exception :class:`RoomError` 
- a class :class:`Room` for create room

This module provides functions and a class for Maze's game's management specifically by representing a room of a maze by a class Room.

"""

class RoomError(Exception):
    """
    Exception for room already open.
    """
    def __init__(self, msg):
        self.message = msg

        
class Room():
    """
    Create room to complete the maze's grid.

    A room is composed by 2 doors (right and bottom) and a tag.
    At the creation, the room have its 2 doors closed.

    The object Room have some many methods:
     - Modifiers :
         * Room.open_right_door()
         * Room.open_bottom_door()
         * Room.set_tag()

     - Selectors :
         * Room.right_door_is_open()
         * Room.bottom_door_is_open()
         * Room.neighbor_top_is_open(second_room)
         * Room.neighbor_left_is_open(second_room)
         * Room.get_tag()

     - Special :
         * Room.printable(list_room)

    :Examples:

    >>> list_room = [ Room() for k in range(10)]
    >>> list_room[3]
     |
    -+
    >>> list_room[2].open_bottom_door()
    >>> list_room[0].open_right_door()
    >>> list_room[4].set_tag('o')
    >>> list_room[2].right_door_is_open()
    False
    >>> list_room[0].right_door_is_open()
    True
    >>> list_room[8].right_door_is_open()
    False
    >>> list_room[1].neighbor_left_is_open(list_room[0])
    True
    >>> list_room[4].get_tag()
    'o'
    >>> list_room[5].get_tag()
    0
    >>> list_room[2]
     |
     +
    >>> str_room = Room.printable(list_room)
    >>> print(str_room)
    |   | | |o| | | | | |
    +-+-+ +-+-+-+-+-+-+-+
    >>> list_room[2].open_bottom_door()
    Traceback (most recent call last):
        ...
    RoomError: The bottom door is already open
    """

    ###########
    # BUILDER #
    ###########
        
    def __init__(self, tag = 0):
        """
        :tag: The tag of the room.
        :type tag: int or str
        :return: New room of a maze's grid.
        :rtype: Room
        :UC: None
        :Examples:

        >>> r = Room()
        >>> r.right_door_is_open()
        False
        >>> r.bottom_door_is_open()
        False
        >>> r.get_tag()
        0
        """
        self.__right_door = False
        self.__bottom_door = False
        self.__tag = tag

    #############
    # MODIFIERS #
    #############

    def open_right_door(self):
        """
        Open the right door of a room.

        :return: None
        :side effect: change the state of the right door (close to open)
        :UC: the right door must be closed
        :Examples:
        
        >>> r = Room()
        >>> r.right_door_is_open()
        False
        >>> r.open_right_door()
        >>> r.right_door_is_open()
        True
        >>> r.open_right_door()    
        Traceback (most recent call last):
            ...
        RoomError: The right door is already open
        """
        if self.right_door_is_open():
            raise RoomError('The right door is already open')
        self.__right_door = True

    def open_bottom_door(self):
        """
        Open the bottom door of a room.

        :return: None
        :side effect: change the state of the bottom door (close to open)
        :UC: the bottom door must be closed
        :Examples:
        
        >>> r = Room()
        >>> r.bottom_door_is_open()
        False
        >>> r.open_bottom_door()
        >>> r.bottom_door_is_open()
        True
        >>> r.open_bottom_door()    
        Traceback (most recent call last):
            ...
        RoomError: The bottom door is already open
        """
        if self.bottom_door_is_open():
            raise RoomError('The bottom door is already open')
        self.__bottom_door = True

    def close_all_door(self):
        """
        Close all doors of a room.

        :return: None
        :side effect:  change the state of the doors (open to close)
        :UC: None
        :Examples:

        >>> r = Room()
        >>> r.open_bottom_door()
        >>> r.bottom_door_is_open()
        True
        >>> r.close_all_door()
        >>> r.bottom_door_is_open()
        False
        """
        self.__right_door = False
        self.__bottom_door = False
        
    def set_tag(self, tag):
        """
        Change the tag of a room.

        :param tag: the desired tag
        :type tag: int or str
        :return: None
        :side effect: change the tag
        :UC: type(tag) == int or tag == 'o'
        :Examples:
        
        >>> r = Room()
        >>> r.get_tag()
        0
        >>> r.set_tag(1)
        >>> r.get_tag()
        1
        """
        assert type(tag) == int or tag == 'o', 'Error, tag must be int or "o"'
        self.__tag = tag


    #############
    # SELECTORS #
    #############

    def right_door_is_open(self):
        """
        Return if the right door of a room is open or not.

        :return:
            * True if the door is open
            * False otherwise
        :rtype: bool
        :UC: None
        :Examples:
        
        >>> r = Room()
        >>> r.right_door_is_open()
        False
        >>> r.open_right_door()
        >>> r.right_door_is_open()
        True
        """
        return self.__right_door

    def bottom_door_is_open(self):
        """
        Return if the bottom door of a room is open or not.

        :return:
            * True if the door is open
            * False otherwise
        :rtype: bool
        :UC: None
        :Examples:
        
        >>> r = Room()
        >>> r.bottom_door_is_open()
        False
        >>> r.open_bottom_door()
        >>> r.bottom_door_is_open()
        True
        """
        return self.__bottom_door


    def get_tag(self):
        """
        Return the tag of a room.
        
        :return: The tag
        :rtype: int or str
        :UC: None
        :Examples:
        
        >>> r = Room()
        >>> r.get_tag()
        0
        >>> r.set_tag(1)
        >>> r.get_tag()
        1
        """
        return self.__tag

    def neighbor_top_is_open(self,second_room):
        """
        Return if the bottom door of a up neighbor room is open.

        :param second_room: a second room
        :type second_room: Room
        :return:
            * True if the top door is open
            * False otherwise
        :rtype: bool
        :UC: None
        :Examples:
        
        >>> r = Room()
        >>> r2 = Room()
        >>> r.neighbor_top_is_open(r2)
        False
        >>> r2.open_bottom_door()
        >>> r.neighbor_top_is_open(r2)
        True
        """
        return second_room.bottom_door_is_open()

    def neighbor_left_is_open(self,second_room):
        """
        Return if the right door of a left neighbor room is open.

        :param second_room: a second room
        :type second_room: Room
        :return:
            * True if the left door is open
            * False otherwise
        :rtype: bool
        :UC: None
        :Examples:
        
        >>> r = Room()
        >>> r2 = Room()
        >>> r.neighbor_left_is_open(r2)
        False
        >>> r2.open_right_door()
        >>> r.neighbor_left_is_open(r2)
        True
        """
        return second_room.right_door_is_open()

    ############
    # SPECIALS # 
    ############

    def __repr_first_line(self):
        """
        Represent the first line of a room.
    
        :return: the line with | (right wall)
        :rtype: str
        :UC: None
        """
        res = ''
        if self.get_tag() == 'o':
            res += 'o'
        else:
            res += ' '

        if self.right_door_is_open():
            res += ' '
        else:
            res += '|'
            
        return res

    def __repr_second_line(self):
        """
        Represent the second line of a room.
        
        :return: the line with '-+' (bottom wall)
        :rtype: str
        :UC: None
        """
        res = ''
        if self.bottom_door_is_open():
            res += ' '
        else:
            res += '-'
        return res + '+'
    
    def __repr__(self):
        """
        :return: The representation of a Room
        :rtype: str
        :Examples:
        
        >>> r = Room()
        >>> r
         |
        -+
        """
        return self.__repr_first_line() + '\n' + self.__repr_second_line()

    def __list_all_range(list_room):
        """
        Represent the first and second lines of a list of rooms in a tuple.

        :param list_room: a list of rooms
        :type list_room: list
        :return: a couple of two strings
        :rtype: tuple
        :UC: None
        """
        if len(list_room) <= 0:
            return ('','')
        else:
            s1, s2 = Room.__list_all_range(list_room[1:])
            return (list_room[0].__repr_first_line() + s1,\
                    list_room[0].__repr_second_line() + s2)

    def printable(list_room):
        """
        Return a string with all rooms, ready to print.

        :param list_room: a list of rooms
        :type list_room: list
        :return: the representation of all rooms
        :rtype: str
        :UC: None
        :Examples:

        >>> list_room = [ Room() for k in range(10)]
        >>> str_room = Room.printable(list_room)
        >>> print(str_room)
        | | | | | | | | | | |
        +-+-+-+-+-+-+-+-+-+-+
        """
        s1, s2 = Room.__list_all_range(list_room)
        return '|' + s1 + '\n+' + s2
            
if __name__ == '__main__':
    import doctest
    doctest.testmod()