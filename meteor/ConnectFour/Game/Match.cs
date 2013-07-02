using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConnectFour.Game
{
    /// <summary>
    /// A Match is an instance of the ConnectFour game, between two 
    /// players, Red and Black.  The Match keeps track of the board
    /// state, who's turn it is, and determines when the game is 
    /// over.
    /// </summary>
    public class Match
    {
        private Container container = new Container();

        public Match()
        {
            // Black goes first. (Smoke before Fire...)
            CurrentPlayer = PieceColor.Black;
        }

        public PieceColor CurrentPlayer { get; private set; }
    }
}
