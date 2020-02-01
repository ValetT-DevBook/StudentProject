-----------------------
 `Graphical Main Maze`
-----------------------

.. automodule:: graphical_main_maze
   :members: __doc__

For users
---------
.. note:: Help for graphical_maze_main.py
   
   NAME
	graphical_main_maze.py

   DESCRIPTION
	This script launch a graphical pattern of you maze.
	command are preceded by - , and in some times followed by specific args precise by [ARG].

   ARGUMENTS
	-h, --help
		Display a quick help.

	-m, --manual
		Display this manual of script with the differents arguments.

	-l, --library
		Show the library of mazes already exist.

	-r, --random 
		[X :int][Y :int]
			Create a random maze with X-by-Y dimension.(By default if X and Y are omises, X=Y=5)

	-f, --from
		[FILE :str]
			Create a maze from a maze .txt files.(You can see files with --l, --library)

	-s, --save 
		[FILE :str]
			Save your maze in a maze .txt file with FILE for name. (You can find your saves in ../maze_library/saves)

	-cw, --change_wall 
		[COLOR_WALL :str]
			Change the color of walls. (By default, walls are black)

	-cr, --change_room
		[COLOR_ROOM :str]
			Change the color of rooms. (By default, rooms are white)

	-cg, --change_goal
		[COLOR_GOAL :str]
			Change the color of the goal. (By default, the goal is blue)

	-cp, --change_path
		[COLOR_PATH :str]
			Change the color of the path. (By default, the path is red)

	-sr, --size_room 
		[SIZE_ROOM :int]
			Change the size of each rooms. (By default, a room's size is 20 pixels)

	-sp, --speed_path 
		[SPEED_PATH :int]
			Change the speed of the path in milliseconds. (By defaut, the path go to 50ms per room)

	-pm, --pac-man
		Pass the maze in the pac-man's mode

Special function
----------------
.. autofunction:: graphical_main_maze.main

Miscellaneous functions
-----------------------
.. autofunction:: graphical_main_maze.coordinate_center

.. autofunction:: graphical_main_maze.binding_buttons

.. autofunction:: graphical_main_maze.unbinding_buttons

Drawing functions
-----------------
.. autofunction:: graphical_main_maze.draw_circle

.. autofunction:: graphical_main_maze.draw_grid

.. autofunction:: graphical_main_maze.erase_circle

Resolution functions
--------------------
.. autofunction:: graphical_main_maze.change_goal

.. autofunction:: graphical_main_maze.lets_go

Display function
----------------
.. autofunction:: graphical_main_maze.display_graphical

Pac-Man functions
-----------------
.. autofunction:: graphical_main_maze.compare_coordinates_pac_man

.. autofunction:: graphical_main_maze.animation_one_room_pac_man

.. autofunction:: graphical_main_maze.recursive_spaw_pac_man

.. autofunction:: graphical_main_maze.draw_spawn_pac_man

.. autofunction:: graphical_main_maze.draw_pac_man

.. autofunction:: graphical_main_maze.lets_go_pac_man


Other function
--------------
.. autofunction:: graphical_main_maze.usage

Other information
-----------------
For colors change arguments :

   .. _fig:tkinter_col:
   .. figure:: ../images/TkInterColorCharts.png
      :align: center
      :width: 55%
      :alt: Authorized colors in Tkinter

      PICTURE 1 :  `Authorized colors in Tkinter`

:doc:`back <index>`


