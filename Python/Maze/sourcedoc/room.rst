----------------------
 :mod:`Room` module
----------------------

The rooms of a maze are objects of the class Room

.. automodule:: room
   :members: __doc__


Exception RoomError
-------------------
.. autoexception:: room.RoomError


Class description
-----------------
.. autoclass::	room.Room


Methods
-------

Modifiers
=========

.. automethod:: room.Room.open_right_door

.. automethod:: room.Room.open_bottom_door

.. automethod:: room.Room.close_all_door

.. automethod:: room.Room.set_tag


Selectors
=========

.. automethod:: room.Room.right_door_is_open

.. automethod:: room.Room.bottom_door_is_open

.. automethod:: room.Room.neighbor_top_is_open

.. automethod:: room.Room.neighbor_left_is_open

.. automethod:: room.Room.get_tag


Special Methods
===============

.. automethod:: room.Room.printable



:doc:`back <index>`
