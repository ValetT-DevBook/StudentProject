--------------------------
 :mod:`Maze` module
--------------------------

.. automodule:: maze
   :members: __doc__


Class MazeState
---------------
.. autoclass::	maze.MazeState


Exception MazeError
-------------------
.. autoexception:: maze.MazeError

Specials functions
------------------
.. autofunction:: maze.adjacent_coordinates


Class Maze
----------
.. autoclass::	maze.Maze


Methods
-------

Builders
========
.. automethod:: maze.Maze.create_from_file


Modifiers
=========
.. automethod:: maze.Maze.set_state

.. automethod:: maze.Maze.set_goal



Selectors
=========

.. automethod:: maze.Maze.get_width

.. automethod:: maze.Maze.get_height

.. automethod:: maze.Maze.get_room

.. automethod:: maze.Maze.get_state

.. automethod:: maze.Maze.get_goal

.. automethod:: maze.Maze.get_solution

Specials methods
----------------
.. automethod:: maze.Maze.change_room

.. automethod:: maze.Maze.change_row

.. automethod:: maze.Maze.end_edit

.. automethod:: maze.Maze.write_in_file

Vous pouvez voir un exemple dans : maze_library/save/writed_in_file_random_example.txt

.. automethod:: maze.Maze.random

.. automethod:: maze.Maze.resolve

.. automethod:: maze.Maze.show_solution

.. automethod:: maze.Maze.hide_solution







:doc:`back <index>`


