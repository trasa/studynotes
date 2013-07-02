ConnectFour - 
Tony Rasa

THINGS of INTEREST:

ConnectFour.Game.Match
----------------------
This class keeps track of the state of a running game: the current player,
whether the game is over or not, that sort of thing.


ConnectFour.Game.Container
--------------------------
Describes the main board of the game.  Use this to examine the state of 
the board and to add pieces to the board.

ConnectFour.Game.ContainerColumn
--------------------------------
A Column of Pieces in the Container.  This class makes sure that when
pieces are dropped in, they 'fall' to the correct position in the column.

ConnectFour.Game.WinDetector
----------------------------
Examines the Container when a new piece is added and figures out if it's
a "win" or not.

Ideally I'd have tests where I pass in a Mock Container so that I could 
fully test the edge cases of the WinDetector.

ConnectFour.Tests
-----------------
Unit tests written during the development of the game classes.  Not 
'Test First,' but certainly Test-Driven.
