----------------------
 :mod:`Stack` module
----------------------

class Stack and methods

* `push`
* `pop`
* `top`
* `is_empty`

:Examples:

>>> stak = Stack()
>>> stak.is_empty()
True
>>> stak.push(1)
>>> stak.is_empty()
False
>>> stak.push(2)
>>> stak.top()
2
>>> stak.pop()
2
>>> stak.top()
1
>>> stak.pop()
1
>>> stak.is_empty()
True
>>> stak.pop()
Traceback (most recent call last):
   ... 
StackEmptyError: empty stack, nothing to pop

Error description
-----------------

.. autoclass:: stack.StackEmptyError

Class Stack
-----------

Methods
=======

.. automethod:: stack.Stack.push

.. automethod:: stack.Stack.pop

.. automethod:: stack.Stack.top

.. automethod:: stack.Stack.is_empty

Special method
==============

.. automethod:: stack.Stack.__init__



:doc:`back <index>`
