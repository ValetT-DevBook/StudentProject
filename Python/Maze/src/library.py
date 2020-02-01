#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""
:mod:`Library` module

:author: BONVOISIN Alexandre, PHU Valentin, VALET Tristan

:date: 2018, November

Provides 

This module provides functions to display a library of files containing mazes.

"""
import os

def compare(s1,s2):
    """
    Compare 2 elements.
    
    :param s1: First element to compare
    :type s1: any type
    :param s2: Second element to compare
    :type s2: same as a
    :return:
       * -1 if s1 < s2
       *  1 if s1 > s2
       *  0 if s1 = s2
    :UC: s1 and s2 must be comparable with <
    :Examples:

    >>> compare(0, 1)
    -1
    >>> compare('a', 'a')
    0
    >>> compare((2, 1), (1, 2))
    1
    """
    assert type(s1) == type(s2),"s1 and s2 haven't the same type"
    if s1 < s2:
        return -1
    elif s1 == s2:
        return 0
    else:
        return 1

def split(l):
    """
    Return a couple (l1,l2) of lists.

    :param l: initial list
    :type l: list
    :return: a couple of two lists of equal length
    :rtype: tuple
    :UC: None
    :Examples:

    >>> l = [3, 1, 4, 1, 5, 9, 2]
    >>> l1, l2 = split(l)
    >>> l3 = l1 + l2
    >>> len(l3) == len(l)
    True
    >>> all(k in l for k in l3)
    True
    """
    n = len(l)
    if n == 0:
        return ([], [])
    elif n == 1:
        return ([l[0]], [])
    else:
        l1, l2 = split(l[2:])
        return ([l[0]] + l1, [l[1]] + l2)
    
def merge(l1,l2, comp = compare):
    """
    Return a list containing all elements from l1 and l2.
    If l1 and l2 are sorted, so is the returned list.

    :param l1: First list
    :type l1: list
    :param l2: Second list 
    :type l2: list
    :param comp: [optional] comparison function (default value is compare)
    :return: a merged list from l1 and l2
    :rtype: list
    :UC: elements of l1 and l2 are comparable
    :Examples:

    >>> merge([1, 3, 4, 9], [1, 2, 5])
    [1, 1, 2, 3, 4, 5, 9]
    """
    if l1 == []:
        return l2.copy()
    elif l2 == []:
        return l1.copy()
    else:
        cmp = comp(l1[0], l2[0])
        if cmp <= 0:
            return [l1[0]] + merge(l1[1:], l2, comp=comp)
        else:
            return [l2[0]] + merge(l1, l2[1:], comp=comp)


def merge_sort(l, comp = compare):
    """
    Return a new list containing elements of l sorted by ascending order.

    :param l: a list to sort
    :type l: list
    :param comp: [optional] comparison function (default value is compare)
    :return: a new list containing elements of l in ascending order
    :rtype: list
    :UC: elements of l are comparable
    :Examples:

    >>> merge_sort([3, 1, 4, 1, 5, 9, 2])
    [1, 1, 2, 3, 4, 5, 9]
    >>> import random
    >>> n = random.randrange(20)
    >>> l = [random.randrange(20) for k in range(n)]
    >>> l1 = merge_sort(l)
    >>> len(l1) == len(l)
    True
    """
    n = len(l)
    if n <= 1:
        return l.copy()
    else:
        l1, l2 = split(l)
        l1s = merge_sort(l1, comp=comp)
        l2s = merge_sort(l2, comp=comp)
        return merge(l1s, l2s, comp=comp)

def check_dimension(string):
    """
    Check the dimension of the maze.
    
    :param string: name of the maze file
    :type string: str
    :return: width and height of the maze
    :rtype: tuple
    :UC: string must be like the pattern : maze_*width*_*height*_*numberOfTheMazeInThisSize*.txt where * are integers
    :Example:
    
    >>> check_dimension('maze_30_30_0.txt')
    (30, 30)
    """
    liste = string.split('_')
    assert len(liste)>3, 'all the file names in the folder should be like : maze_*width*_*height*_*numberOfTheMazeInThisSize*.txt where * are integers'
    try:
        size =(int(liste[1]),int(liste[2]))
        return size
    except ValueError:
        raise Exception('all the file names in the folder should be like : maze_*width*_*height*_*numberOfTheMazeInThisSize*.txt where * are integers') from None

def partition_list(l):
    """
    Rank maze files by their size
    
    :param l: a list containing files names of mazes files in the directory 
    :type l: list
    :return: dictionnary where keys are sizes of mazes and values are names of files which contain a maze pattern of this size
    :rtype: dict
    :UC: None
    :Examples:
    
    >>> partition_list(['maze_10_10_0.txt', 'maze_10_10_1.txt', 'maze_10_10_2.txt', 'maze_10_10_3.txt', 'maze_10_10_4.txt', 'maze_10_10_5.txt','maze_5_5_9.txt', 'maze_6_4_1.txt'])
    {(6, 4): ['maze_6_4_1.txt'], (5, 5): ['maze_5_5_9.txt'], (10, 10): ['maze_10_10_0.txt', 'maze_10_10_1.txt', 'maze_10_10_2.txt', 'maze_10_10_3.txt', 'maze_10_10_4.txt', 'maze_10_10_5.txt']}
    >>> partition_list(['maze_10_10_0.txt'])
    {(10, 10): ['maze_10_10_0.txt']}
    """
    if l == []:
        return dict()
    else:        
        dictio = partition_list(l[1:])
        if l[0][-4:] == '.txt':
            dim = check_dimension(l[0])
            if dim in dictio:
                dictio[dim] = [l[0]] + dictio[dim] 
            else:
                dictio[dim] = [l[0]]
        return dictio
        
def biggest(l1,l2=[],l3=[]):
    """
    Compare 3 list an return the biggest length among them.
    
    :param l1: First list
    :type l: list
    :param l2: [optionnal] Second list
    :type l: list
    :param l3: [optionnal] Third list
    :type l: list
    :return: the length of the biggest list
    :rtype: int
    :UC: None
    :Example:
    
    >>> biggest(['a','b','c'])
    3
    >>> biggest(['a','b','c'],[1,2,3,4,5,6],[1,2,3,'a','b','c'])
    6
    >>> biggest(['a','b','c'],[1,2,3,4,5,6],[1,2,3,'a','b','c','d'])
    7
    """
    n = len(l1)
    if n < len(l2):
        n = len(l2)
    if n < len(l3):
        n = len(l3)
    return n

def print_line(str1,str2,str3):
    """
    Puts files names of maze file into a specific format.

    :param str1: the first file name
    :type str1: str
    :param str2: the second file name
    :type str2: str
    :param str3: the third file name
    :type str3: str
    :return: a string which contain str1,str2,str3 in a specific format :
        * if the length of the string is < 25, some space are added between strings
    :rtype: str
    :UC: None
    :Examples:
    
    >>> print_line('maze_5_5_0.txt','maze_6_4_1.txt','maze_10_6_1.txt')
    'maze_5_5_0.txt           maze_6_4_1.txt           maze_10_6_1.txt          '
    """
    return '{:25}'.format(str1) + '{:25}'.format(str2) + '{:25}'.format(str3)

def print_row(l1,l2,l3):
    """
    Display each elements of lists in a row between 3 column, elements in the index 0 in the first row etc.
    
    :param l1: First list (that is the first column)
    :type l1: list
    :param l2: Second list (that is the second column)
    :type l2: list
    :param l3: Third list (that is the third column)
    :type l3: list
    :return: None
    :side effect: print list content like a table
    :UC: lists content must be only strings
    :Examples:
    
    >>> print_row(['1','2','3'],['a','b','c'],['1','2','3'])
    1                        a                        1                        
    2                        b                        2                        
    3                        c                        3
    >>> print_row(['maze_5_5_0.txt','maze_5_5_1.txt','maze_5_5_2.txt'],['maze_6_4_1.txt'],['maze_10_6_1.txt'])
    maze_5_5_0.txt           maze_6_4_1.txt           maze_10_6_1.txt          
    maze_5_5_1.txt                                                             
    maze_5_5_2.txt
    """
    borne = biggest(l1,l2,l3)
    i = 0
    while i < borne:
        str1, str2, str3 = '', '', ''
        if i < len(l1):
            str1 = l1[i]
        if i < len(l2):
            str2 = l2[i]
        if i < len(l3):
            str3 = l3[i]
        print(print_line(str1,str2,str3))
        i += 1
    print('')

def print_library(directory):
    """
    Display all available maze files in a directory.
    
    :param directory: name of the directory which contain maze file
    :type directory: str
    :return: None
    :side effect: print all available files
    :UC: the directory must contain files that have a name like the pattern : maze_*width*_*height*_*numberOfTheMazeInThisSize*.txt where * are integers
    :Examples:
    
    >>> print_library('../maze_library/create')
    Maze 5x5:                Maze 6x4:                Maze 10x6:               
    maze_5_5_0.txt           maze_6_4_1.txt           maze_10_6_1.txt          
    maze_5_5_1.txt                                                             
    maze_5_5_2.txt                                                             
    maze_5_5_3.txt                                                             
    maze_5_5_4.txt                                                             
    maze_5_5_5.txt                                                             
    maze_5_5_6.txt                                                             
    maze_5_5_7.txt                                                             
    maze_5_5_8.txt                                                             
    maze_5_5_9.txt                                                             
    <BLANKLINE>
    Maze 10x10:              Maze 15x12:              Maze 20x20:              
    maze_10_10_0.txt         maze_15_12_0.txt         maze_20_20_0.txt         
    maze_10_10_1.txt         maze_15_12_1.txt         maze_20_20_1.txt         
    maze_10_10_2.txt         maze_15_12_2.txt         maze_20_20_2.txt         
    maze_10_10_3.txt         maze_15_12_3.txt         maze_20_20_3.txt         
    maze_10_10_4.txt         maze_15_12_4.txt         maze_20_20_4.txt         
    maze_10_10_5.txt         maze_15_12_5.txt         maze_20_20_5.txt         
    maze_10_10_6.txt         maze_15_12_6.txt         maze_20_20_6.txt         
    maze_10_10_7.txt         maze_15_12_7.txt         maze_20_20_7.txt         
    maze_10_10_8.txt         maze_15_12_8.txt         maze_20_20_8.txt         
    maze_10_10_9.txt         maze_15_12_9.txt         maze_20_20_9.txt         
    <BLANKLINE>
    Maze 30x30:              Maze 40x40:              Maze 50x50:              
    maze_30_30_0.txt         maze_40_40_0.txt         maze_50_50_0.txt         
    maze_30_30_1.txt         maze_40_40_1.txt         maze_50_50_1.txt         
    maze_30_30_2.txt         maze_40_40_2.txt         maze_50_50_2.txt         
    maze_30_30_3.txt         maze_40_40_3.txt         maze_50_50_3.txt         
    maze_30_30_4.txt         maze_40_40_4.txt         maze_50_50_4.txt         
    maze_30_30_5.txt         maze_40_40_5.txt         maze_50_50_5.txt         
    maze_30_30_6.txt         maze_40_40_6.txt         maze_50_50_6.txt         
    maze_30_30_7.txt         maze_40_40_7.txt         maze_50_50_7.txt         
    maze_30_30_8.txt         maze_40_40_8.txt         maze_50_50_8.txt         
    maze_30_30_9.txt         maze_40_40_9.txt         maze_50_50_9.txt
    """
    library = os.listdir(directory)
    library = merge_sort(library)
    dictio = partition_list(library)

    keys = list(dictio.keys())
    keys = merge_sort(keys)

    nb_type = 0
    n = len(keys)
    
    while nb_type < n:
        l1 = dictio[keys[nb_type]]
        l2, l3 = [], []
        if nb_type + 1 < n:
            l2 = dictio[keys[nb_type + 1]]
        if nb_type + 2 < n:
            l3 = dictio[keys[nb_type + 2]]
            
        str1 = 'Maze {:d}x{:d}:'.format(keys[nb_type][0],keys[nb_type][1])
        str2 = 'Maze {:d}x{:d}:'.format(keys[nb_type+1][0],keys[nb_type+1][1])
        str3 = 'Maze {:d}x{:d}:'.format(keys[nb_type+2][0],keys[nb_type+2][1])
        print(print_line(str1,str2,str3))
        
        print_row(l1,l2,l3)
        nb_type += 3
        
if __name__=='__main__':
    import doctest
    doctest.testmod(optionflags=doctest.NORMALIZE_WHITESPACE | doctest.ELLIPSIS, verbose=False)