-------------
 `Main Maze`
-------------

.. automodule:: main_maze
   :members: __doc__

For users
---------
.. note:: Help for maze_main.py
   
   NAME
	   main_maze.py

   DESCRIPTION
	   This script launch a pattern of you maze inside your terminal.

   ARGUMENTS
	   -h, --help
		   Display the manual of script with the differents arguments

	   -l, --library
		   Show the library of mazes already exist
	
	   -r, --random
		[X : int][Y :int]
		   Create a random maze with X-by-Y dimension.(By default if X and Y are omises, X=Y=5)

	   -f, --from
		 [FILE :str]
		   Create a maze from a maze .txt files. (You can see the files with --l, --library)

	   -e, --edit 
		[X :int][Y :int]
		   Create an editable maze with X-by-Y dimension. (By default if X and Y are omises, X=Y=5)

	   -rs, --resolve
		[X :int][Y :int][X_goal :int][Y_goal :int]
		   Display on your maze the resolution beginning in the coordinates (X, Y) to the coordinates (X_goal,Y_goal).
		   (By default if X and Y are omises, (X,Y)=(0,0))
	           (By default if X_GOAL and Y_GOAL are omises, (X_GOAL, Y_GOAL)=(width-1, height-1))

	   -s, --save 
		[FILE :str]
		   Save your maze in a maze .txt file with FILE for name. (You can find your saves in ../maze_library/saves)
		
   IMPORTANT:
	   -rs or --resolve must be given before -s or --save

Special function
----------------
.. autofunction:: main_maze.main

Others functions
----------------
.. autofunction:: main_maze.usage

.. autofunction:: main_maze.manual

.. autofunction:: main_maze.main_resolve

.. autofunction:: main_maze.main_random

.. autofunction:: main_maze.edit

:doc:`back <index>`


